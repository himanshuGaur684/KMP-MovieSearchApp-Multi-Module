import SwiftUI
import Shared
import SearchUi
 
import CoreNavigation

import DetailsUi

struct ContentView: View {
    
 @StateObject private var navHostController = NavHostController()
 
    var body: some View {
        VStack {
            NavigationStack(path: $navHostController.path){
                SearchView(){movieId in
            
                    navHostController.navigateTo(path: Destination.details(movieId))
                }.navigationDestination(for: Destination.self) { destination in
                    switch destination {
                    case .search: EmptyView()
                    case .details(let movieId): DetailsView(movieId: movieId)
                    }
                }
            }
            
        }
        .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .top)
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
