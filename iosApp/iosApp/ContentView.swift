import SwiftUI
import Shared
import SearchUi
import KMPObservableViewModelCore
import CoreNavigation
import DetailsUi

struct ContentView: View {

    @StateObject private var router: AppRouter = AppRouter()

    var body: some View {

        NavigationStack(path: $router.path) {
            SearchView(action: { movieId in
                router.navigate(to: AppDestination.details(movieId: movieId))
            })
            .navigationDestination(for: AppDestination.self) { destination in

                switch destination {
                case .search: EmptyView()
                case .details(movieId: let movieId): MovieDetailsView(movieId: movieId)

                }
            }
        }
        .environmentObject(router)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
