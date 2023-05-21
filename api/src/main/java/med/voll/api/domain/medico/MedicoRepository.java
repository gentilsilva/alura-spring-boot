package med.voll.api.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    Medico getReferenceByIdAndAtivoTrue(Long id);

    @Query("""
           SELECT m FROM Medico m WHERE m.ativo = TRUE and m.especialidade = :especialidade\s
           AND m.id NOT IN (
                SELECT c.medico.id FROM Consulta c
                WHERE\s
                c.data = :data
           )\s
           ORDER BY RAND() limit 1                  \s
           """)
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);
}
