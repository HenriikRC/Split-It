package sdu.splitit.viewmodel.authentication

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.auth.FlowType
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.Storage
import io.github.jan.supabase.storage.storage
import sdu.splitit.BuildConfig
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SupabaseClient {

    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient {
        return createSupabaseClient(
            supabaseKey = BuildConfig.API_KEY,
            supabaseUrl = BuildConfig.API_BASE_URL,
        ) {
            install(Auth) {
                flowType = FlowType.PKCE
                scheme = "app"
                host = "supabase.com"
            }
            install(Postgrest)
            install(Storage)
        }
    }

    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient): Postgrest {
        return client.postgrest
    }

    @Provides
    @Singleton
    fun provideSupabaseAuth(client: SupabaseClient): Auth {
        return client.auth
    }

    @Provides
    @Singleton
    fun provideSupabaseStorage(client: SupabaseClient): Storage {
        return client.storage
    }
}