# Imporve image loading with blurhash
[![BlurHashPainter](https://jitpack.io/v/wajahat-iqbal/BlurHashPainter.svg)](https://jitpack.io/#wajahat-iqbal/BlurHashPainter)

Blurhash is a technique for encoding the visual representation of an image into a compact string of characters. This string, or hash, can be used to generate a low-resolution preview of the image, which can be displayed while the full-resolution image is still loading.

The main benefit of using blurhash is that it allows you to show a preview of an image before it fully loads, which can greatly improve the user experience. For example, when scrolling through a list of images, blurhash can be used to show a low-resolution preview of each image as soon as it comes into view, rather than waiting for the full-resolution image to load. This can make the list feel much more responsive and smooth.

https://user-images.githubusercontent.com/20109427/213825075-44efd19b-f9ea-4f0b-8085-4bb13eb8c25a.mov

# How add this to your project 

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
 
```
dependencies {
        implementation 'com.github.wajahat-iqbal:BlurHashPainter:1.2'
}
```
  
  
  

# How to use 
```
AsyncImage(
  contentDescription = "",
  modifier = Modifier
      .height(100.dp)
      .width(100.dp),
  model = ImageRequest.Builder(
      LocalContext.current
  ).data(item.original.src).crossfade(true).build(),
  placeholder = BlurHashPainter(
      item.blurHash,
      item.landscape.width,
      item.landscape.height,
      1F,
      0.1F
  ),
  contentScale = ContentScale.FillBounds,
)
}
```


# To improve image loading with BlurhashPainter, you can follow these steps:

1. Use this hash to generate a low-resolution preview of the image, which can be displayed while the full-resolution image is still loading.
Implement the Blurhash technique in your Android app using Jetpack and the AsyncImage component, as demonstrated in the project linked in your post.

2. Store the Blurhash of the images in the database, but make sure to keep the X and Y component values lower than 4 to avoid affecting the recomposing of the image.

3. Use a low scale value in the BlurHashPainter when displaying the image as a preview, but use a higher value if displaying the image in full screen.

4. To use this technique in your project, you can use the package "com.github.wajahat-iqbal:BlurHashPainter:1.2" and add it to your dependencies.

Check out Wolt's Blurhash website (https://blurha.sh/) for further information and resources.
