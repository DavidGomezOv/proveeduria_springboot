package com.example.proveeduria_api.service;

import com.example.proveeduria_api.models.CreateUserRequestModel;
import com.example.proveeduria_api.models.CreateUserResponseModel;
import com.example.proveeduria_api.models.RolModel;
import com.example.proveeduria_api.models.SessionModel;
import com.example.proveeduria_api.models.UserModel;
import com.example.proveeduria_api.repository.RolRepository;
import com.example.proveeduria_api.repository.SessionRepository;
import com.example.proveeduria_api.repository.UserRepository;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio que maneja la lógica de negocio relacionada con los usuarios.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final RolRepository rolRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * Inyección del repositorio de usuarios.
     *
     * @param repository repositorio JPA de usuarios
     */
    public UserService(UserRepository userRepository,
            RolRepository rolRepository,
            SessionRepository sessionRepository) {
        this.userRepository = userRepository;
        this.rolRepository = rolRepository;
        this.sessionRepository = sessionRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Obtiene todos los usuarios registrados.
     *
     * @return lista de usuarios
     */
    public List<UserModel> getAll() {
        return userRepository.findAll();
    }

    /**
     * Crea un usuario: valida correo único, hashea contrasena, asigna rol y crea
     * sesión inicial "Inactiva". Retorna UserResponse con info (sin contrasena).
     */
    @Transactional
    public CreateUserResponseModel createUser(CreateUserRequestModel req) {

        if (userRepository.findByCorreo(req.getCorreo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo");
        }

        // crear entidad UserModel
        UserModel user = new UserModel();
        user.setCedula(req.getCedula());
        user.setNombre(req.getNombre());
        user.setApellidos(req.getApellidos());
        user.setCorreo(req.getCorreo());
        user.setContrasena(passwordEncoder.encode(req.getContrasena()));

        // asignar rol si viene rolId
        if (req.getRolId() != null) {
            RolModel rol = rolRepository.findById(req.getRolId())
                    .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado: " + req.getRolId()));
            user.setRol(rol);
        }

        // guardar usuario primero (para obtener id y persistencia)
        UserModel savedUser = userRepository.save(user);

        // crear sesión inicial (Inactiva)
        SessionModel session = new SessionModel();
        session.setSessionStatus("Activa");
        session.setUser(savedUser);
        SessionModel savedSession = sessionRepository.save(session);

        // enlazar sesión al usuario (si quieres que la relación en memoria quede consistente)
        savedUser.setSesion(savedSession);
        userRepository.save(savedUser); // opcional, para sincronizar FK si es necesario

        String rolNombre = savedUser.getRol() != null ? savedUser.getRol().getNombreRol() : null;
        return new CreateUserResponseModel(savedUser.getId(),
                savedUser.getCedula(),
                savedUser.getNombre(),
                savedUser.getCorreo(),
                rolNombre,
                savedSession.getId(),
                savedSession.getSessionStatus());
    }
}
