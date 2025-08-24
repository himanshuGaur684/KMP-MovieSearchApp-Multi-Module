// The Swift Programming Language
// https://docs.swift.org/swift-book

import SwiftUI
import Shared
import Combine
import KMPObservableViewModelCore
import KMPObservableViewModelSwiftUI

public struct MovieDetailsView: View {

    @StateViewModel private var viewModel: DetailsViewModel = DetailsViewModelProvider().provideDetailsViewModel()

    public init(movieId: String) {
        self.movieId = movieId
    }

    public let movieId: String

    public var body: some View {
        ScrollView {

            if viewModel.uiState.isLoading {
                ZStack {
                    ProgressView()
                }
                .frame(maxWidth: .infinity)
                .frame(maxHeight: .infinity)
            }

            if viewModel.uiState.error.isEmpty == false {
                ZStack {
                    Text("Something went wrong.")
                }
                .frame(maxWidth: .infinity)
                .frame(maxHeight: .infinity)
            }

            if let data = viewModel.uiState.data {
                VStack {

                    AsyncImage(url: URL(string: data.imageUrl)) { phase in

                        switch phase {
                        case .empty: ZStack {
                            ProgressView()
                        }
                        .frame(maxWidth: .infinity)
                        .frame(height: 600)
                        case .failure(_): ZStack {
                            Text("Not found")
                        }
                        .frame(maxWidth: .infinity)
                        .frame(height: 600)
                        case .success(let image):
                            image.resizable()
                                .scaledToFill()
                                .frame(maxWidth: .infinity)
                                .frame(height: 600)
                                .clipped()

                        @unknown default:
                            EmptyView()
                        }

                    }

                    Text(data.title)
                        .font(.system(size: 36))
                        .fontWeight(.bold)
                        .padding(.horizontal,12)
                        .padding(.top, 16)


                    Text(data.description)
                        .padding(.horizontal)
                        .padding(.top, 12)

                }
            }
        }
        .ignoresSafeArea()
        .onAppear(perform: {
            Task {
                await viewModel.getMovieDetails(movieId: movieId)
            }
        })
    }
}
