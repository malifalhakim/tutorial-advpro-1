# Tutorial 1

Nama : M.Alif Al Hakim \
NPM : 2206081250 \
Pemrograman Lanjut B

### Refleksi 1
You already implemented two new features using Spring Boot. Check again your source code
and evaluate the coding standards that you have learned in this module. Write clean code
principles and secure coding practices that have been applied to your code. If you find any
mistake in your source code, please explain how to improve your code. Please write your
reflection inside the repository's README.md file.

> Dalam mengimplementasikan fitur edit dan *delete*, saya sudah menerapkan beberapa prinsip, diantaranya :
> * Penggunaan nama yang memiliki arti yang sudah sesuai dengan kegunaan fungsi atau variabel.
> * Membagi *code* menggunakan beberapa *method* dengan kegunaan yang spesifik.
> * Penulisan code yang lebih eksplisit atau mudah dipahami untuk menghindari *comment* yang tidak diperlukan.
> * Mengikuti standar *Layout* dan *Formatting*, seperti penggunaan *new line* yang mendandakan konsep baru atau berbeda dibanding kode sebelumnya.
> * Penggunaan variabel *private* pada Objek yang digunakan.
> * Melakukan *error-handling* dengan membuat *exception* jika terjadi kesalahan pada *method* yang dibuat.
> * Tidak mengembalikan objek *null* pada *method* yang dibuat.
> * Melakukan input *validation* pada field `productName` dan `productQuantity`.
> * Mencegah masuknya karakter yang tidak sesuai pada field `productName` dan `productQuantity` 

> Beberapa hal yang dapat ditingkatkan dari code yang telah saya buat, diantaranya penggunaan prinsip *auntentication* dan *authorization* serta melakukan input *validation* dan *sanization* juga pada *server-side*.

### Refleksi 2
After writing the unit test, how do you feel? How many unit tests should be made in a
class? How to make sure that our unit tests are enough to verify our program? It would be
good if you learned about code coverage. Code coverage is a metric that can help you
understand how much of your source is tested. If you have 100% code coverage, does
that mean your code has no bugs or errors?

> Setelah menulis unit test sebelumnya, saya menjadi lebih memahami bagaimana membuat unit test dan memahami kenapa membuat unit test adalah hal yang penting. Unit test akan membantu kita dalam me-*maintain* *app* yang kita buat sehingga proses *scalability* dapat berjalan lebih lancar
> Menurut saya, kita dapat memastikan unit test yang telah dibuat dengan mengecek apakah unit test sudah mengecek semua fungsi yang ada, mengecek berbagai skenario input yang mungkin terjadi dalam menggunakan suatu fungsi, dan mengecek skenario positif atau negatif suatu fungsi. Selain itu, kita juga dapat memastikan unit test yang telah dibuat cukup atau tidak dengan menggunakan metrik *code coverage*. Dimana suatu unit test setidaknya memiliki coverage 60-70% atau code coverage optimal sekitar 70% - 80%.
> Akan tetapi perlu diingat bahwa 100% *code coverage* tidak menjamin program yang dibuat bebas dari error atau *bugs*. Hal ini dikarenakan *code coverage* hanya merepresentasikan persentase code yang telah di-*cover* oleh unit test, dimana 100% *code coverage* berarti setiap baris kode dieksekusi saat *testing*. Setiap *function/method* yang dicek pada unit test masih belum tentu telah mengecek semua skenario atau edge case yang bisa dites sehingga program dengan *code coverage* unit test sebesar 100% pun masih bisa mengalami error/bugs.


Suppose that after writing the CreateProductFunctionalTest.java along with the
corresponding test case, you were asked to create another functional test suite that
verifies the number of items in the product list. You decided to create a new Java class
similar to the prior functional test suites with the same setup procedures and instance
variables.
What do you think about the cleanliness of the code of the new functional test suite? Will
the new code reduce the code quality? Identify the potential clean code issues, explain
the reasons, and suggest possible improvements to make the code cleaner!

> Menurut saya, pengimplementasian seperti itu akan membuat code pada program menjadi tidak bersih. Jika *setup procedure* dan *instance variabel* yang digunakan sama, maka terdapat duplikasi kode di antara dua *class code* tersebut. Duplikasi kode ini akan membuat kode menjadi lebih kotor dan program akan lebih sulit untuk di-*mantain*. Hal ini dikarenakan jika terjadi perubahan setup prosedur, maka kita harus mengubah di kedua *class* tersebut. Menurut saya, salah satu solusi yang dapat diterapkan adalah dengan menggabungkan kedua test tersebut pada *class* yang sama sehingga *setup procedure* hanya perlu dilakukan sekali. Selain itu, kedua test memiliki cara kerja atau karakteristik yang mirip sehingga dapat digabungkan pada satu *class* saja.

# Tutorial 2
You have implemented a CI/CD process that automatically runs the test suites, analyzes code quality, and deploys to a PaaS. Try to answer the following questions in order to reflect on your attempt completing the tutorial and exercise.

List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
> - Terdapat *public modifier* pada salah satu unit test yang saya buat. Pada JUnit5 sebenarnya *test classes* atau *methods* dapat memiliki *visibility* apapun kecuali *private*. Akan tetapi, JUnit5 sendiri lebih merekomendasikan menggunakan *default modifier*. Oleh karena itu, untuk mengatasi masalah ini, saya menghapus modifier *public* pada method tersebut sehingga mengubahnya ke modifier *default*.
> - Terdapat peringatan untuk menguji satu method saja dalam method *Assert*. Sebelum saya menggunakan kode seperti berikut ``assertThrows(IllegalArgumentException.class, () -> productRepository.edit(newProduct, oldProduct.getProductId()));``. Akan tetapi hal ini, dapat menghasilkan ketidakjelasan method mana yang menghasilkan exception. Untuk itu saya menyimpan productId ke dalam sebuah variable string terlebih dahulu sehingga kode akhir terlihat seperti berikut ``assertThrows(IllegalArgumentException.class, () -> productRepository.edit(newProduct, oldProductId));``

Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
> Menurut saya, implementasi saat ini sudah memenuhi definisi *Continous Integration* dan *Continuous Deployment*. Hal ini dikarenakan pada *workflows* yang saya tulis telah dilakukan proses pengintegrasian dan pengetesan secara otomatis untuk setiap perubahan yang saya *push* atau *merge* pada repositori. Dengan adanya workflow tersebut, saya dapat memverifikasi perubahan yang saya lakukan tidak merubah fungsionalitas kode sebelumnya. Selain itu, pada implementasi ini juga sudah ditambahkan *workflows* untuk *code analysis* yang dapat membantu saya memverfikasi kode saya secara otomatis dengan lebih detail. Untuk *continuous deployment*, saya menggunakan Koyeb untuk melakukan *deployment* otomatis. Dengan menggunakan Koyeb yang sudah tergintegrasi dengan GitHub Actions, saya dapat melakukan *deployment* secara otomatis setiap terjadi *push* ataupun *merge* ke *branch main* sehingga sudah memenuhi definisi atau syarat *continous deployment*.

# Tutorial 3
Apply the SOLID principles you have learned. You are allowed to modify the source code according to the principles you want to implement. Please answer the following questions:

1. Explain what principles you apply to your project!
> - Single Responsibility Principle <br>
> Prinsip ini membahas bahwa suatu *class* hanya memiliki satu tanggung jawab (*responsibility*). <br>
> Pada tutorial ini, contoh SRP yang diterapkan diantaranya:
>   - Memisahkan *class* *Controller* *Product* dan *Car*. Kedua *class* tersebut memiliki tanggung jawab yang berbeda dan tidak terkait satu sama lain.
>   - Memindahkan proses penetapan Id pada *constructor* *class* menjadi pada method create di product repositori. Hal ini bertujuan untuk membuat *class* Model hanya memiliki satu tanggung jawab tertentu saja.
> - Open-Closed Principle <br>
> Prinsip ini membahas bahwa suatu entitas *software* (*classes*, *modules*, *function*, etc.) terbuka untuk *extension*, tapi tertutup untuk modifikasi.
> Pada tutorial ini, contoh OCP yang diterapkan adalah penggunaan *interface* *service* dengan metode-metode yang telah didefinisikan dan kemudian dibuat kelas-kelas yang mengimplementasikan interface tersebut. Dengan cara ini, kita dapat menambahkan operasi-operasi untuk kelas tertentu dengan lebih mudah tanpa mengubah operasi utama.
> - Liskov Substitution Principle <br>
> Prinsip ini membahas bahwa jika suatu program terdefinisi untuk suatu objek dengan *type* T, maka perilaku dari program tersebut tidak berubah jika objek diganti dengan yang memiliki *type* S (dimana S adalah *sub-type* dari T).
> Pada tutorial ini, setelah dilakukannya pemisahan antara *Product* dan *Car* Controller, tidak ada hubungan *inheritance* yang terjadi pada setiap kelasnya. Oleh karena itu, tidak ada terjadinya pelanggaran prinsip ini.
> - Interface Segregation Principle <br>
> Prinsip ini membahas bahwa suatu *interface* yang besar dapat dipecah menjadi *interface* yang lebih kecil dengan fokus tertentu. Hal ini bertujuan untuk memastikan bahwa *class* yang mengimplementasikan interface tersebut menggunakan/memerlukan semua method pada *interface* tersebut. Pada tutorial ini, contoh ISP yang diterapkan adalah adanya penggunaan *interface* (*Interface* CarService dan ProductService) yang memiliki satu tujuan/tanggung jawab saja sehingga kelas yang mengimplementasikannya pasti memerlukannya.
> - Dependency Inversion Principle <br>
> Prinsip ini membahas bahwa modul tingkat tinggi tidak boleh bergantung pada modul tingkat rendah. Akan tetapi, keduanya harus bergantung pada abstraksi.
> Pada tutorial ini, contoh DIP yang diterapkan diantaranya:
>   - Mengubah objek `private CarServiceImpl carService` menjadi `private CarService carService;` sehingga modul terkait dengan abstraksinya.

2. Explain the advantages of applying SOLID principles to your project with examples.

> - Dengan menerapkan SRP, saya dapat memiliki kode yang mudah dibaca dan lebih *maintainable*. Selain itu, prinsip ini juga akan memudahkan dalam melakukan *testing* dan bersifat *lower coupling*. Contohnya, jika saya mempunyai kelas yang terfokuskan/terbagi tanggung jawabnya dengan baik, maka saya dapat dengan lebih mudah untuk menentukan apa yang perlu di test dan memudahkan untuk melakukan test karena minimnya dependensi dengan komponen lain.
> - Dengan menerapkan OCP, saya dapat dengan mudah menambahkan fungsionalitas tanpa mengubah kode yang telah ada sehingga dapat mencegah munculnya bug. Contohnya, jika saya ingin mmembuat fungsionalitas baru, saya dapat membuat kelas baru misalnya, AdvanceCarServiceImpl yang juga mengimplementasikan CarService.
> - Dengan menerapkan LSP, saya menggunakan program yang sudah (tanpa mengubahnya) untuk memproses suatu objek dengan type atau sub-type tertentu. Misalnya jika saya mempunyai *class* Bird dan *class* Eagle yang merupakan sub-class dari Bird, maka saya dapat menggunakan method fly() kepada dua objek *class* tersebut tanpa perlu mengubah-ubah implementasi fly().
> - Dengan menerapkan ISP, saya dapat memiliki kode yang mudah dibaca dan lebih mudah dipelihara. Contohnya, jika terdapat kode yang mengimplementasikan suatu interface yang spesifik dan minimal, maka kelas yang mengimplementasikan akan lebih mudah dipahami dan dipelihara karena tidak ada method yang tidak diimplementasikan atau diimplementasi dengan kode *dummy*.
> - Dengan menerapkan DIP, saya dapat memiliki kode yang lebih mudah untuk di-*maintain* karna saya dapat mengubah perubahan pada *lower-level module* tanpa mempengaruhi *high-level* modul. Contohnya, saya dapat mengubah implementasi CarServiceImpl tanpa mengubah kode pada CarController. Selain itu, prinsip ini dapat memudahkan untuk melakukan test dikarenakan tidak adanya dependensi yang kuat antara dua komponen/modul.

3. Explain the disadvantages of not applying SOLID principles to your project with examples.

> - Jika SRP tidak diterapkan, maka kode akan lebih sulit untuk dibaca dan dipelihara. Hal ini dikarenakan tidak ada pemisah antara fungsionalitas yang satu dan yang lain. Selain itu, tidak menerapkan SRP dapat membuat proses *testing* menjadi lebih susah. Contohnya jika saya menggabungkan beberapa *class* dalam satu module, akan mengakibatkan kode menjadi susah dibaca dan susah untuk di-*test* dikarenakan terdapat resiko *coupling* antar kelas.
> - Jika OCP tidak diterapkan, maka kode akan lebih susah untuk di-*maintain*. Contohnya jika saya ingin menambah fungsionalitas suatu kelas dan saya tidak menerapkan OCP, maka saya kemungkinan perlu mengubah kode yang sudah ada.
> - Jika LSP tidak diterapkan, maka kode akan menjadi tidak fleksibel. Misalnya jika saya memiliki program yang terdefinisi dengan suatu type T, maka jika saya menggunakan program untuk sub-type dari T, program harus diubah terlebih dahulu.
> - Jika ISP tidak diterapkan, maka pada kode akan kemungkinan dimana *class* tidak mengimplementasikan semua method dengan benar karena ada beberapa method yang tidak diperlukan. Hal ini dapat menimbulkan kode menjadi lebih sulit dibaca dan susah dipelihara. Contohnya jika saya menggunakan Interface yang besar dan interface diimplement oleh banyak kelas dan jika suatu ketika interface perlu diubah, maka saya perlu mengubah banyak kelas yang mengimplementasikannya. 
> - Jika DIP tidak diterapkan, maka kode akan lebih sulit untuk di-*maintain*. Contohnya, jika saya menggunakan Service Implementation dibanding menggunakan Interfacenya, maka akan ada resiko *coupling* sehingga modul controller saya menjadi bergantung dengan service implementation saya dan juga menyebabkan kode menjadi susah untuk diubah atau di-*scale*.


# Tutorial 4
You have followed the Test-Driven Development workflow in the Exercise. Now answer these questions:
1. Reflect based on Percival (2017) proposed self-reflective questions (in “Principles and Best Practice of Testing” submodule, chapter “Evaluating Your Testing Objectives”), whether this TDD flow is useful enough for you or not. If not, explain things that you need to do next time you make more tests.
    > Menurut saya TDD *flow* cukup membantu saya dalam mengimplementasikan project ini. Pada project ini, test yang digunakan
   > sudah cukup benar dimana telah dianalisi tiap kasus dari suatu fungsi (*happy* dan *unhappy* *test*). Hal ini meyakinkan dan memudahkan saya dalam membuat setiap implementasi dengan baik\
   > Salah satu hal yang mungkin dapat ditingkatkan adalah membuat *functional test* untuk beberapa fitur. Hal ini dapat meningkatkan tingkat keyakinan saya dalam membuat suatu fitur. 
   > Dari segi maintainability, TDD *flow* dapat memberikan saya kepercayaan diri untuk melakukan refactor code. Hal ini dikarenakan saya menjadi lebih tau
   > Apa *requirement* atau input yang dibutuhkan dan *output* yang harus dihasilkan. Selain dengan TDD *flow*, saya dapat mengurangi pembuatan implementasi dua method atau fungsi yang saling terkait.
   > Dengan *TDD*, saya harus membuat test-nya terlebih dahulu sehingga mengurangi kecenderungan membuat dua method atau fungsi yang saling terkait. Dari segi *productive workflow*, TDD *flow* juga membantu saya.
   > Salah satunya dengan TDD *flow* saya mendapatkan *feedback* error yang terjadi dengan lebih cepat. Selain itu, proses penjalanan *test* juga berlangsung cepat dan bisa menjalankan subset tertentu saja.

2. You have created unit tests in Tutorial. Now reflect whether your tests have successfully followed F.I.R.S.T. principle or not. If not, explain things that you need to do the next time you create more tests.
   > Pada tutorial ini, menurut saya, F.I.R.S.T principle sudah diterapkan dengan cukup baik. Test berlangsung cepat dan tidak mengganggu workflow lain. Unit test dan functional test telah dipisahkan dan tidak ada peristiwa
   > menunggu suatu subsystem/fungsi pada saat menggunakan unit test. Test yang digunakan juga terisolasi dengan baik dimana tidak ada unit test yang saling bergantung atau mempengaruhi. Terdapat definisi "setUp" dan "tearDown" untuk menghindari duplikasi
   > dan membantu mengisolasi test case. Selain itu, telah digunakan mock Object untuk menghindari dependensi dengan objek lain. Test juga mampu berjalan dengan konsisten. Test juga bersifat self-validating dimana digunakan Assertions untuk menghasilkan test yang strict dan benar.
   > Terakhir, test juga telah mengecek semua kasus mulai dari happy hingga unhappy test sehingga test case sudah meng-cover semua kemungkinan hasil.

