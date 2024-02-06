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



