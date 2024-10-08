# Unit Tests with Mockito

## Unit Test Nedir?

Unit test, yazılımın en küçük parçalarını (unit) test etmek için kullanılan bir yöntemdir. Bu testler, genellikle bir sınıf veya metot üzerinde gerçekleştirilir ve beklenen çıktının doğru olup olmadığını kontrol eder.

## Neden Yapılır?

Unit testler, yazılım geliştirme sürecinde önemli bir rol oynar. Aşağıdaki nedenlerden dolayı kullanılırlar:

- **Hata Ayıklama**: Geliştirme sürecinde hataların erken tespit edilmesini sağlar.
- **Kod Kalitesi**: Kodun doğru çalıştığını doğrular ve gelecekteki değişikliklerin mevcut işlevselliği bozmadığını garanti eder.
- **Geliştirme Süresi**: Testler, kodun yeniden kullanılabilirliğini artırarak geliştiricilerin daha hızlı ilerlemesini sağlar.

## Nasıl İmplement Edilir?

Mockito kullanarak birim testleri yazmak için aşağıdaki adımları izleyebilirsiniz:

1. **Bağımlılıkları Ekleyin**: `build.gradle` dosyanıza Mockito bağımlılığını ekleyin.
   ```groovy
   testImplementation 'org.mockito:mockito-core:3.11.2' // Mockito ile mocklayıp, testleri istediğimiz gibi simüle edebiliriz.
   testImplementation 'androidx.arch.core:core-testing:2.1.0' // //InstantTaskExecutorRule gibi kurallar ile LiveData güncellemelerinin anında tetiklenmesini sağlarız.
   
2. **Test Sınıfını Oluşturun**: Test edilecek sınıf için bir test sınıfı oluşturun..
   ```groovy
   class UserViewModelTest {
   @get:Rule
    val rule = InstantTaskExecutorRule()
   
    private lateinit var viewModel: UserViewModel
    private lateinit var mockRepo: UserRepository

    @Before
    fun setup() {
        mockRepo = Mockito.mock(UserRepository::class.java)
        viewModel = UserViewModel(mockRepo)
    }

    @Test
    fun test_UserList_Only_1_User() {
        Mockito.`when`(repo.getUsers())
            .thenReturn(listOf(User("user1")))

        viewModel.getList()

        assert(viewModel.listLive.value?.size == 1)
    }
   

    @Test
    fun testUserLoginSuccess() {
        // Test senaryosunu burada yazın
    }
   
   }

