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

> Menurut saya, pengimplementasian seperti itu akan membuat code pada program menjadi tidak bersih. Jika *setup procedure* dan *instance variabel* yang digunakan sama, maka terdapat duplikasi kode di antara dua *class code* tersebut. Duplikasi kode ini akan membuat kode menjadi lebih kotor dan program akan lebih sulit untuk di-*mantain*. Hal ini dikarenakan jika terjadi perubahan setup prosedur, maka kita harus mengubah di kedua *class* tersebut. Menurut saya, salah satu solusi yang dapat diterapkan adalah dengan menggabungkan kedua test tersebut pada *class* yang sama sehingga *setup procedure* hanya perlu dilakukan sekali. Selain itu, kedua test memiliki cara kerja atau karakteristik yang mirip sehingga dapat digabungkan pada satu *class* saja.]

# Tutorial 2
You have implemented a CI/CD process that automatically runs the test suites, analyzes code quality, and deploys to a PaaS. Try to answer the following questions in order to reflect on your attempt completing the tutorial and exercise.

List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.
> - Terdapat *public modifier* pada salah satu unit test yang saya buat. Pada JUnit5 sebenarnya *test classes* atau *methods* dapat memiliki *visibility* apapun kecuali *private*. Akan tetapi, JUnit5 sendiri lebih merekomendasikan menggunakan *default modifier*. Oleh karena itu, untuk mengatasi masalah ini, saya menghapus modifier *public* pada method tersebut sehingga mengubahnya ke modifier *default*.
> - Terdapat peringatan untuk menguji satu method saja dalam method *Assert*. Sebelum saya menggunakan kode seperti berikut ``assertThrows(IllegalArgumentException.class, () -> productRepository.edit(newProduct, oldProduct.getProductId()));``. Akan tetapi hal ini, dapat menghasilkan ketidakjelasan method mana yang menghasilkan exception. Untuk itu saya menyimpan productId ke dalam sebuah variable string terlebih dahulu sehingga kode akhir terlihat seperti berikut ``assertThrows(IllegalArgumentException.class, () -> productRepository.edit(newProduct, oldProductId));``

Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!
> Menurut saya, implementasi saat ini sudah memenuhi definisi *Continous Integration* dan *Continuous Deployment*. Hal ini dikarenakan pada *workflows* yang saya tulis telah dilakukan proses pengintegrasian dan pengetesan secara otomatis untuk setiap perubahan yang saya *push* atau *merge* pada repositori. Dengan adanya workflow tersebut, saya dapat memverifikasi perubahan yang saya lakukan tidak merubah fungsionalitas kode sebelumnya. Selain itu, pada implementasi ini juga sudah ditambahkan *workflows* untuk *code analysis* yang dapat membantu saya memverfikasi kode saya secara otomatis dengan lebih detail. Untuk *continuous deployment*, saya menggunakan Koyeb untuk melakukan *deployment* otomatis. Dengan menggunakan Koyeb yang sudah tergintegrasi dengan GitHub Actions, saya dapat melakukan *deployment* secara otomatis setiap terjadi *push* ataupun *merge* ke *branch main* sehingga sudah memenuhi definisi atau syarat *continous deployment*.