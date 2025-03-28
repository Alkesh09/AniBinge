AniBinge is an Android app that fetches a list of top anime series, displaying detailed information and trailers for each anime.

Features:
1. Architecture: Designed using MVVM with a Single Activity Architecture.
2. API Integration: Utilized Retrofit and Gson for seamless API calls.
3. Asynchronous Operations: Implemented Coroutines and Flows for efficient asynchronous data fetching.
4. Navigation: Used Navigation Component with a Navigation Graph for smooth navigation.
5. UI Implementation: Built the user interface using XML layouts.
6. Dependency Injection: Intefrated Dagger hilt for dependency Injection.

Limitations:
1. The anime details API does not provide cast information, so it is not displayed in the UI.
2. The trailer links retrieved from the API are YouTube URLs, which are not supported by ExoPlayer. To resolve this, the android-youtube-player library was integrated for video playback.

APP Link- [Download APK](https://drive.google.com/file/d/1NDQjRP3ZdpDqacsJWx_DGFaP5iNSziPy/view?usp=sharing)
