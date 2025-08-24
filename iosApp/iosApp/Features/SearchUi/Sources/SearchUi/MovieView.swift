//
// Created by Himanshu Gaur on 23/08/25.
//

import Foundation
import Shared
import SwiftUI
import KMPObservableViewModelSwiftUI

struct MovieView: View {

    private let movie: Movie

    public init(movie: Movie) {
        self.movie = movie
    }

    var body: some View {
        ZStack {

            if (movie.imageUrl.isEmpty) {
                ZStack {
                    Text("This image is not available")
                        .tint(.black)
                }
                .frame(maxWidth: .infinity)
                .frame(height: 300)
            } else {
                AsyncImage(
                    url: URL(string: movie.imageUrl)
                ) { phase in
                    switch phase {
                    case .empty:
                        ZStack {
                            ProgressView()
                        }
                        .frame(maxWidth: .infinity)
                        .frame(height: 300)

                    case .failure(_):
                        ZStack {
                            Text("Something went wrong")
                        }
                        .frame(maxWidth: .infinity)
                        .frame(height: 300)

                    case .success(let image):
                        image.resizable()
                            .scaledToFill()
                            .frame(height: 300)
                            .frame(maxWidth: .infinity)
                            .clipShape(RoundedRectangle(cornerRadius: 12))
                            .padding(.horizontal)
                            .padding(.vertical, 4)

                    @unknown default:
                        EmptyView()
                    }
                }
            }

        }
    }
}
