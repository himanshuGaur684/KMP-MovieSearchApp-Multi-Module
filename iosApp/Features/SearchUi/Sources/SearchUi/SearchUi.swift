// The Swift Programming Language
// https://docs.swift.org/swift-book

import Shared
import KMPObservableViewModelCore
import KMPObservableViewModelSwiftUI

import SwiftUI

public struct SearchView : View {
    
    @StateViewModel var viewModel: SearchViewModel = SearchViewModelProvider().provideSearchViewModel()
    
    let action : (String)->Void
    
    public  init(action: @escaping (String)-> Void){
        self.action = action
    }
    
    public var body: some View {
        
        
        VStack{
            
            TextField("Search Movies",text: Binding(get: {
                viewModel.query
            },set:{
                viewModel.updateQuery(q: $0)
            })).textFieldStyle(RoundedBorderTextFieldStyle())
                .padding()
            
            Spacer()
            
            
            if(viewModel.uiState.isLoading){
                ZStack{
                    ProgressView()
                }.frame(maxWidth: .infinity, maxHeight: .infinity)
            }
            
            if(viewModel.uiState.error.isEmpty==false){
                ZStack{
                    Text(viewModel.uiState.error)
                }.frame(maxWidth: .infinity, maxHeight: .infinity)
            }
            
            if let data = viewModel.uiState.data{
                
                ScrollView{
                    
                         ForEach(data,id: \.self) { item in
                            
                            if(item.imageUrl.isEmpty){
                                ZStack{
                                    Text("No Image url found")
                                }.frame(maxWidth: .infinity)
                                    .frame(height: 200)
                            }else{
                                AsyncImage(url: URL(string: item.imageUrl)) { phase in
                                    
                                    switch phase {
                                    case .empty:
                                        ZStack{
                                            ProgressView()
                                        }.frame(maxWidth: .infinity)
                                            .frame(height: 400)
                                    case .success(let image):
                                        image.resizable()
                                            .frame(maxWidth: .infinity)
                                            .frame(height: 400)
                                            .scaledToFill()
                                            .clipShape(RoundedRectangle(cornerRadius: 12))
                                            .padding(.horizontal)
                                            .clipped()
                                    case .failure(let error):
                                        ZStack{
                                            Text("Error loading image \(error)")
                                        }.frame(maxWidth: .infinity)
                                            .frame(height: 400)
                                    @unknown default:
                                        EmptyView()
                                    }
                                    
                                }.onTapGesture {
                                    action(item.id)
                                }
                            }
                        }
                    
                }
                
            }
        }
    }
}

