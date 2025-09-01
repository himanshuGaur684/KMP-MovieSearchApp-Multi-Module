// The Swift Programming Language
// https://docs.swift.org/swift-book


import SwiftUI
import KMPObservableViewModelCore
import KMPObservableViewModelSwiftUI
@preconcurrency import Shared

public struct DetailsView : View{
    
    
    let movieId : String
    
    public init(movieId:String){
        self.movieId = movieId
    }
 
    @StateViewModel var viewModel = DetailsViewModelProvider().provideDetailsViewModel()
    
    public var body : some View{
        
        ZStack{
            
            if viewModel.uiState.isLoading{
                ZStack{
                    ProgressView()
                }
            }
            
            if viewModel.uiState.error.isEmpty==false{
                ZStack{
                    Text(viewModel.uiState.error)
                }
            }
            
            if let data = viewModel.uiState.movieDetails{
                
                ScrollView{
                    VStack{
                        
                        
                        AsyncImage(url: URL(string: data.imageUrl)){phase in
                            
                            switch phase {
                            case .empty:
                                ZStack{
                                    ProgressView()
                                }.frame(maxWidth: .infinity)
                                    .frame(height: 600)
                            case .success(let image):
                                image.resizable().frame(maxWidth: .infinity).frame(height: 600)
                                    .scaledToFill()
                                    .clipped()
                            case .failure(let error):
                                ZStack{
                                    Text(error.localizedDescription)
                                }.frame(maxWidth: .infinity)
                                    .frame(height: 600)
                            @unknown default:
                                EmptyView()
                            }
                            
                            
                        }
                        
                        Spacer(minLength: 24)

                        Text(data.title)
                            .font(.title)
                        
                        Spacer(minLength: 12)
                        
                        Text(data.overView).padding(.horizontal,12)
                        
                        
                        
                    }
                }
                
            }
            
            
        }.ignoresSafeArea().onAppear {
            Task{
                viewModel.getMovies(id: movieId)
            }
        }
        
        
    }
    
}
