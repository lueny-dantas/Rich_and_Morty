package paixao.lueny.rickandmorty.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import paixao.lueny.rickandmorty.data.mappers.CharactersMapper
import paixao.lueny.rickandmorty.data.retrofitBuilder.RetrofitBuilder
import paixao.lueny.rickandmorty.domain.models.Character
import retrofit2.HttpException
import java.util.Locale


class CharactersDataSource: PagingSource<Int, Character>() {
     private val api get() = RetrofitBuilder().create(ApiService::class.java)
     private var searchTextFilter: String? = null
     private var statusFilter: Character.Status? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val filtersParams = mutableMapOf<String, String>()

            if(searchTextFilter != null){
                filtersParams["name"] = searchTextFilter!!
            }

            if(statusFilter != null) {
                filtersParams["status"] = statusFilter!!.name.lowercase(Locale.getDefault())
            }

            val result = api.getAllCharacters(
                page = params.key ?: STARTING_PAGE_INDEX,
                params = filtersParams
            ).results ?: emptyList()

            val characters: MutableList<Character> = mutableListOf()

            for(characterResponse in result){
                val character = CharactersMapper.toDomain(characterResponse)
                characters.add(character)
            }

            LoadResult.Page(
                data = characters,
                prevKey = params.key,
                nextKey = params.key?.plus(1) ?: STARTING_PAGE_INDEX.plus(1)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

     fun updateFilters(newSearchTextFilter: String? = null, newStatusFilter: Character.Status? = null){
         searchTextFilter = newSearchTextFilter
         statusFilter = newStatusFilter
     }

    suspend fun getCharacter(characterId: Int): Character {
        val characterResponse = api.getCharacter(characterId)
        val character: Character = CharactersMapper.toDomain(characterResponse)

         return character
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

     override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return null
     }
 }




