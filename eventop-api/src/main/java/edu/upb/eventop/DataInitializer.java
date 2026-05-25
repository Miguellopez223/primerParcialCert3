package edu.upb.eventop;

import edu.upb.eventop.repository.EmpresaRepository;
import edu.upb.eventop.repository.EventosRepository;
import edu.upb.eventop.repository.UserRepository;
import edu.upb.eventop.repository.entity.Empresa;
import edu.upb.eventop.repository.entity.Eventos;
import edu.upb.eventop.repository.entity.User;
import edu.upb.eventop.repository.enums.RoleType;
import edu.upb.eventop.repository.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final EmpresaRepository empresaRepository;
    private final EventosRepository eventosRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        init();
    }

    private void init() {
        // Seed usuario root
        if (userRepository.count() == 0) {
            userRepository.save(User.builder()
                    .username("root")
                    .email("root@upb.com")
                    .role(RoleType.ROLE_ROOT)
                    .nombres("Ricardo")
                    .apellidos("Laredo")
                    .status(UserStatus.ACTIVO)
                    .password(passwordEncoder.encode("Abc123**"))
                    .build());
            log.info("[DataInitializer] Usuario root creado");
        }

        // Seed empresa
        if (empresaRepository.count() == 0) {
            Empresa empresa = new Empresa();
            empresa.setNit("1234567890");
            empresa.setNombre("Empresa 1");
            empresa.setDescripcion("Primera empresa de prueba para eventos");
            empresa = empresaRepository.save(empresa);
            log.info("[DataInitializer] Empresa creada con id: {}", empresa.getId());

            // Seed evento (asociado a la empresa)
            if (eventosRepository.count() == 0) {
                Eventos evento = new Eventos();
                evento.setNombre("Evento 1");
                evento.setDescripcion("Primer evento de prueba");
                evento.setEmpresa(empresa);
                evento = eventosRepository.save(evento);
                log.info("[DataInitializer] Evento creado con id: {}", evento.getId());
            }
        }
    }
}
