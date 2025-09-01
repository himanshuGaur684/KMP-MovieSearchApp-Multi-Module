//
//  NavHostController.swift
//  CoreNavigation
//
//  Created by Himanshu Gaur on 29/08/25.
//

import SwiftUI

public class NavHostController : ObservableObject{
    
    
    @Published public  var path: NavigationPath = NavigationPath()
    
    public init(){}
    
    public func navigateTo(path: any Hashable){
        self.path.append(path)
    }
    
    
    public  func popBackStack(){
        path.removeLast()
    }
    
}
