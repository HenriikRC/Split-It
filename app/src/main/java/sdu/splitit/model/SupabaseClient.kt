package sdu.splitit.model

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import sdu.splitit.BuildConfig

object SupabaseClient {
    val client = createSupabaseClient(
        supabaseKey = BuildConfig.API_KEY,
        supabaseUrl = BuildConfig.API_BASE_URL,
    ) {
        install(Auth)
        install(Postgrest)
    }
}
