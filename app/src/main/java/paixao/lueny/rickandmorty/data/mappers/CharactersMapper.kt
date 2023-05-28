package paixao.lueny.rickandmorty.data.mappers

import paixao.lueny.rickandmorty.data.model.CharacterResponse
import paixao.lueny.rickandmorty.data.model.LocationResponse
import paixao.lueny.rickandmorty.data.model.OriginResponse
import paixao.lueny.rickandmorty.domain.models.Character

object CharactersMapper {
    fun toDomain(characterResponse: CharacterResponse): Character {
        return Character(
            name = characterResponse.name,
            image = characterResponse.image,
            species = characterResponse.species,
            gender = toDomainGender(genderResponse = characterResponse.gender),
            origin = toDomainOrigin(originResponse = characterResponse.origin),
            location = toDomainLocation(locationResponse = characterResponse.location),
            status = toDomainStatus(statusResponse = characterResponse.status),
        )
    }

    private fun toDomainOrigin(originResponse: OriginResponse): Character.Origin {
        return originResponse.run {
            Character.Origin(name = name)
        }

    }

    private fun toDomainLocation(locationResponse: LocationResponse): Character.Location {
        return locationResponse.run {
            Character.Location(name = name)
        }

    }

    private fun toDomainGender(genderResponse: String): Character.Gender {
        return when (genderResponse) {
            "Female" -> Character.Gender.Female
            "Male" -> Character.Gender.Male
            "Genderless" -> Character.Gender.Genderless
            else -> Character.Gender.Unknown
        }

    }

    private fun toDomainStatus(statusResponse: String): Character.Status {
        return when (statusResponse){
            "Alive" -> Character.Status.Alive
            "Dead" -> Character.Status.Dead
            else -> Character.Status.Unknown
        }

    }

}