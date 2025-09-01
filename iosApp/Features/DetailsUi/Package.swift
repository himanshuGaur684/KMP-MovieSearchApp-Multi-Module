// swift-tools-version: 6.1
// The swift-tools-version declares the minimum version of Swift required to build this package.

import PackageDescription

let package = Package(
    name: "DetailsUi",
    platforms: [.iOS(.v16),.macOS(.v11)],
    products: [
        // Products define the executables and libraries a package produces, making them visible to other packages.
        .library(
            name: "DetailsUi",
            targets: ["DetailsUi"]),
    ],
    
    dependencies: [
        .package(url: "https://github.com/rickclephas/KMP-NativeCoroutines.git", exact: "1.0.0-ALPHA-46"),
        .package(url: "https://github.com/rickclephas/KMP-ObservableViewModel.git", from: "1.0.0-BETA-13"),
        
    ],
    
    targets: [
        // Targets are the basic building blocks of a package, defining a module or a test suite.
        // Targets can depend on other targets in this package and products from dependencies.
        .target(
            name: "DetailsUi",
            dependencies: [
                // Swift Concurrency implementation
                .product(name: "KMPNativeCoroutinesAsync", package: "KMP-NativeCoroutines"),
                // Combine implementation
                .product(name: "KMPNativeCoroutinesCombine", package: "KMP-NativeCoroutines"),
                // RxSwift implementation
                .product(name: "KMPNativeCoroutinesRxSwift", package: "KMP-NativeCoroutines"),
                .product(name: "KMPObservableViewModelCore", package: "KMP-ObservableViewModel"),
                .product(name: "KMPObservableViewModelSwiftUI", package: "KMP-ObservableViewModel")
                
            ]
        ),
        
    ]
)
