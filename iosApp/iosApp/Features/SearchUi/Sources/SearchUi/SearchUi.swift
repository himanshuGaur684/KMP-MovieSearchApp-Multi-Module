// The Swift Programming Language
// https://docs.swift.org/swift-book


import Shared
import Combine
import KMPObservableViewModelCore
import KMPObservableViewModelSwiftUI
import SwiftUI

public struct SearchView: View {


    public init(action: @escaping (String) -> Void) {
        self.action = action
    }

    @StateViewModel private var searchViewModel: SearchViewModel = SearchViewModelProvider().provideSearchViewModel()

    public let action: (String) -> Void


    public var body: some View {

        VStack {
            TextField("Search...", text: Binding<String>(
                get: { searchViewModel.query },
                set: { searchViewModel.updateQuery(q: $0) }
            )).textFieldStyle(RoundedBorderTextFieldStyle())
                .padding()

            Spacer()
            VStack {

                if searchViewModel.uiState.isLoading {
                    ZStack {
                        ProgressView()
                    }
                    .frame(maxWidth: .infinity)
                    .frame(maxHeight: .infinity)
                }

                if (searchViewModel.uiState.error.isEmpty == false) {
                    ZStack {
                        Text(searchViewModel.uiState.error)
                    }
                }


                if let data = searchViewModel.uiState.data {
                    ScrollView {
                        LazyVStack {
                            ForEach(data, id: \.self) { item in
                                MovieView(movie: item)
                                    .onTapGesture {
                                        action(String(item.id))
                                    }
                            }
                        }
                    }
                }
            }
            .frame(maxWidth: .infinity)
            .frame(height: .infinity)
        }

    }
}

