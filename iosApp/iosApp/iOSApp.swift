import SwiftUI
import Shared

@main
struct iOSApp: App {

    init() {
        AppDiSetupKt.doInitKoin()
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
