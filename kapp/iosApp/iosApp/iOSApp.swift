import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
                .onOpenURL { url in
                    // handle the in coming url or call a function
                    // Sends the full URI on to the singleton
                    KDeeplinkExternalUriHandler.shared.onNewUri(uri: url.absoluteString)
                }
        }
    }
}