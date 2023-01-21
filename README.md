# Imporve image loading with blurhash

Blurhash is a technique for encoding the visual representation of an image into a compact string of characters. This string, or hash, can be used to generate a low-resolution preview of the image, which can be displayed while the full-resolution image is still loading.

The main benefit of using blurhash is that it allows you to show a preview of an image before it fully loads, which can greatly improve the user experience. For example, when scrolling through a list of images, blurhash can be used to show a low-resolution preview of each image as soon as it comes into view, rather than waiting for the full-resolution image to load. This can make the list feel much more responsive and smooth.

https://user-images.githubusercontent.com/20109427/213825075-44efd19b-f9ea-4f0b-8085-4bb13eb8c25a.mov

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
