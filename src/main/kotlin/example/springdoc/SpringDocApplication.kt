package example.springdoc

import com.fasterxml.jackson.annotation.JsonIgnore
import java.time.LocalDate
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.PastOrPresent
import org.hibernate.annotations.Type
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.repository.CrudRepository

@SpringBootApplication
class SpringDocApplication

fun main(args: Array<String>) {
    runApplication<SpringDocApplication>(*args)
}

@Suppress("unused")
interface GameDayRepository : CrudRepository<GameDay, UUID>

@Suppress("unused")
@Entity(name = "gameDays")
class GameDay(
    @Id
    @Suppress("unused")
    @Type(type = "uuid-char")
    @Column(length = 36, unique = true, nullable = false)
    var id: UUID? = null,

    @field:NotNull
    @field:PastOrPresent
    var date: LocalDate,

    @field:Valid
    @field:NotEmpty
    @JoinColumn(name = "game_day_id", nullable = false)
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var matches: Set<Match>,
)

@Suppress("unused")
@Entity(name = "matches")
class Match(
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var goalsTeamA: Long,

    var goalsTeamB: Long,
)