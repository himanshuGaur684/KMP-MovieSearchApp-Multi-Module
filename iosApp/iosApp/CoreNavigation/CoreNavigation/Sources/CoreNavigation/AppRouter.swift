//
//  AppRouter.swift
//  CoreNavigation
//
//  Created by Himanshu Gaur on 23/08/25.
//

import SwiftUI

public class AppRouter: ObservableObject {

    public init() {
    }

    @Published public var path = NavigationPath()

    public func navigate(to destination: any Hashable) {
        path.append(destination)
    }

    public func popBackStack() {
        path.removeLast()
    }

    public func popToRoot() {
        path.removeLast(path.count)
    }

}
