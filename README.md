# 一、思路分析
首先根据Scene.java推知，被修改后的图片中隐含了一个.class文件的信息，在main函数中从这张图片中按一定规则读取出这个类并且创建类对象，最后用这个类对象进行排序操作。  
而执行加密操作的main函数，在example.encoder.SteganographyFactory类中。在其main函数中调用的getSteganography函数里，通过作为参数传进来的图片URL参数，读取了图片信息，并将图片内容作为参数传递调用了位于example.encoder.SteganographyEncoder类中的encoderFile函数。在此函数中，简而言之，将“文件名长度（4字节） + 文件长度（4字节） + 文件名 + 文件内容”转换成字符串，再转化为若干个byte，再将每个byte拆分为若干个二进制数，依次存入图片上每个像素点的三原色值的最后一个二进制位。最后按照文件名，将修改后的图片保存。  
由于修改后的每一个像素点颜色只发生了微小变化，所以肉眼看不出来。当这些完成之后，在运行Scene时便可以调用decode函数来解密，将之前的class信息从图片中还原出来。在修改了example.encoder.SteganographyEncoder中的decode()函数后（修改为读取出文件长度后，再读取等同于文件长度的字节数，并且将返回值中的前(8 + 文件名长度)个字节舍弃），便可以正确返回之前存储在图片里的class数据了。最后在Scene的main函数中，便可以构造一个这个class的对象，用其进行排序操作。  
  
*在阅读代码过程中我注意到，文件长度信息存储在加密信息中的4~7字节处，占用4字节。如果文件长度超过4字节表示的整数范围（虽然可能性很小），那么即使图片的像素点数量足够存储文件，也会出现错误。

# 二、图片URL

QuickSort:   
    `file:/S191220016.QuickSorter.png` 

ShellSort:   
    `file:/S191220016.ShellSorter.png`

*注意：由于package限制，这两个图片加密的class只能在我的W02的S191220016文件夹下的Scene.java中运行，在本次作业的Scene中无法直接运行

# 三、排序视频

QuickSort:
    [![asciicast](https://asciinema.org/a/437900.svg)](https://asciinema.org/a/437900)
ShellSort:
    [![asciicast](https://asciinema.org/a/437901.svg)](https://asciinema.org/a/437901)

# 四、交换测试
    等待其他人ing