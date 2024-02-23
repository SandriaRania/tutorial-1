Modul 3:
1) Explain what principles you apply to your project!
Dari kelima prinsip SOLID, saya telah mengimplementasikan setidaknya tiga dari lima prinsip itu, yaitu
Single Responsibility Principle(SRP), Interface Segregation Principle(ISP), dan Dependency Inversions Principle(DIP).

Untuk menerapkan SRP dan ISP, saya memisah setiap fungsi dalam CarRepository menjadi fungsi dalam file masing-masing, sehingga
ketika saya ingin merubah salah satu fungsi, hal ini tidak akan memengaruhi fungsi lainnya. Selain itu, hal ini dapat 
mengurangi kemungkinan ditambahnya interface yang kurang relevan dan memberikan penampilan yang lebih ringkas dan enak dilihat.

Untuk menerapkan DIP, saya memisah CarController yang sebelumnya extend ProductController menjadi file sendirinya, sehingga
mengubah agar CarController tidak sepenuhnya bergantung pada ProductController. Selain itu, saya juga menambah HomeController
sebagai titik awal dari kedua controller tersebut.

2) Explain the advantages of applying SOLID principles to your project with examples.

Saya merasa saya akan bisa mengurangi jumlah error yang tidak diantisipasi dengan menggunakan prinsip SOLID. Sebagai contoh,
ketika saya mengedit sesuatu seperti CarCreate, saya akan merasa lega bahwa hal ini tidak akan memengaruhi CarDelete, CarUpdate,
dan lainnya, sehingga saya bisa memastikan bahwa seluruh error yang akan muncul akan muncul hanya karena CarCreate, 
dan bukan karena faktor di luar fungsi tersebut.

3) Explain the disadvantages of not applying SOLID principles to your project with examples.

Saya merasa lebih bingung ketika saya belum menggunakan prinsip SOLID dalam proyek saya. Sebagai contoh, sebelum pemisahan 
di controller, saya khawatir bahwa error di salah satu fungsi akan berdampak secara keseluruhan. Ketika yang muncul hanyalah
WhiteLabel Error dengan berbagai baris error yang saya tidak mengerti, tentunya saya akan merasa kebingungan apakah yang 
bermasalah hanyalah di bagian CarController yang saya edit sebelumnya, atau karena suatu hal yang saya tidak ketahui telah
berdampak dan membuat masalah di ProductController.

Modul 2: 
1. List the code quality issue(s) that you fixed during the exercise and explain your strategy

Saya menggunakan SonarCloud untuk mengecek kode saya. Saat berhasil tersambung dengan repository
saya, muncul laporan bahwa kode saya memiliki 1 Bug dalam kategori Reliability dan 2 Code Smells
dalam kategori Maintanibility.

Mengenai bug, masalah yang saya dapat adalah, ternyata table saya dalam ProductList.html memerlukan 
sebuah deskripsi, maka salah satu solusi yang saya lakukan dengan melihat opsi yang disediakan SonarCloud
adalah menambahkan deskripsi <p> sebelum <table> lalu menggunakan atribut aria-describedby untuk merujuk
ke deskripsi tersebut.

Selanjutnya adalah Code Smells pertama, yaitu pada ProductRepository.java, saya harus memastikan bahwa remove()
pada function delete berfungsi dengan benar (iterasi pada seluruh elemen list), maka solusinya adalah merubah
loop menjadi for (int i = productData.size() - 1; i >= 0; i--) { agar menyesuaikan dengan elemen yang
berkurang.

Terakhir untuk Code Smells kedua, pada ProductServiceImpl.java, lastProductId berupa static field diupdate
di non-static set, maka solusinya adalah membuat fungsi updateId static terpisah yang akan dipanggil 
setiap membuat produk.

2. Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current
   implementation has met the definition of Continuous Integration and Continuous
   Deployment? Explain the reasons (minimum 3 sentences)!

Ya, menurut saya workflow saya pada saat ini telah memenuhi definisi CI/CD karena telah mengautomotatisasi
job yang perlu dilakukan dengan menggunakan tools yang tersedia. Sebagai contoh, saya telah menggunakan tools Scorecard
untuk mengecek workflow kode saya dan memastikan kode saya telah bekerja dengan baik. Selain itu, saya juga menambah
tools Sonarcloud untuk mengecek suatu aspek lain dari kode saya, yaitu aspek clean code. Dengan menggunakan tools Sonarcloud, 
saya dapat merapikan kode saya sesuai prinsip yang berlaku secara umum untuk memastikan reliabilitas dan pemeliharaan.

Link aplikasi: https://eshop-rania-tutorial-1.koyeb.app/ 
Sudah bisa deploy tapi tidak ada isinya


Modul 1: \
Reflection 1: \
You already implemented two new features using Spring Boot. Check again your source code
and evaluate the coding standards that you have learned in this module. Write clean code
principles and secure coding practices that have been applied to your code. If you find any
mistake in your source code, please explain how to improve your code. 

Dalam modul ini, saya sudah mencoba mengaplikasikan beberapa prinsip Clean Code yang telah
diajarkan, sebagai contoh, saya menamakan variabel saya sejelas mungkin agar saya tidak 
akan kebingungan jika suatu saat saya melihat lagi kodingan saya di masa depan. Selain itu,
saya juga memastikan fungsi-fungsi saya dibuat sesingkat, jelas, dan ringkas mungkin, sehingga
dapat diperkirakan apa pemanfaatan fungsi tersebut tanpa perlu komentar tambahan. 

Mengenai kalimat terakhir, salah satu kesalahan yang saya lakukan dalam mengerjakan modul ini
terjadi ketika saya ingin merombak ulang fungsi Edit dan Delete product, namun saya melakukan hal
tersebut saat saya sedang mengerjakan branch unit-test, bukan di main, edit-product, maupun delete-product, 
alhasil saat saya sadar sudah terlanjur commit, saya harus merge ke setiap branch tersebut dan membuat 
workflow saya mirip semua tanpa terlihat progress dan perubahannya. Salah satu solusi yang saya dapat 
lakukan di masa depan adalah membuat branch lain khusus untuk perubahan-perubahan yang berhubungan dengan
branch lainnya, dan memastikan saya tidak akan mencampur-aduk workflow saya ketika mencommit suatu perubahan. 

Reflection 2: 
1. After writing the unit test, how do you feel? How many unit tests should be made in a
class? How to make sure that our unit tests are enough to verify our program? It would be
good if you learned about code coverage. Code coverage is a metric that can help you
understand how much of your source is tested. If you have 100% code coverage, does
that mean your code has no bugs or errors?

Setelah selesai mengerjakan unit-test, saya merasa sangat lega karena bagian tersebut akhirnya selesai. 
Menurut saya, jumlah test yang dibutuhkan untuk suatu kelas dipengaruhi faktor-faktor seperti kompleksitas
dan panjang fungsi, sehingga tidak ada jumlah tetap yang diharuskan untuk suatu unit-test, sebagai contoh 
untuk suatu class yang pendek dan simpel, mungkin 3-5 saja cukup, namun jumlah ini jauh dari cukup untuk class
yang lebih besar. Untuk memastikan unit-test kita dapat memverifikasi program kita, kita harus memikirkan setiap
kemungkinan dan skenario yang dapat terjadi untuk fungsi tersebut, namun, hal ini tidak dapat memastikan bahwa
kode kita akan sempurna. Bahkan jika kita memiliki 100% code coverage pun, mungkin bisa saja ada kasus yang tidak
terpikirkan, sehingga kode kita tidak sepenuhnya pasti bebas dari error dan bugs, kecuali yang sudah kita 
pikirkan dan pastikan aman dengan unit-test yang dirancang sebelumnya.

2. Suppose that after writing the CreateProductFunctionalTest.java along with the
corresponding test case, you were asked to create another functional test suite that
verifies the number of items in the product list. You decided to create a new Java class
similar to the prior functional test suites with the same setup procedures and instance
variables.
What do you think about the cleanliness of the code of the new functional test suite? Will
the new code reduce the code quality? Identify the potential clean code issues, explain
the reasons, and suggest possible improvements to make the code cleaner!

Walaupun prinsip clean code merekomendasikan bahwa setiap fungsi cukup melakukan satu hal saja, namun
membuat sebuah functional test suite yang mirip dengan test suite sebelumnya sepertinya suatu hal yang
redundan. Sepertinya akan lebih efektif jika kita membuat functional test baru yang berisi variabel dan kasus
yang lebih variatif dan mencakup hal-hal yang tidak dites dalam functional test sebelumnya, sehingga penambahan 
functional test baru tidak sia-sia, melainkan dapat memberikan manfaat terhadap aspek testing source code.



