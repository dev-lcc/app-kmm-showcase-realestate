//
//  ContentViewModel.swift
//  iosApp
//
//  Created by Lawrence Cendana on 10/7/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import data
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCore
import Combine

@MainActor
class ContentViewModel: ObservableObject {
    
    @Published var properties: [ModelProperty] = []
    
    init() {
        let propertiesRepository = CoreDataResolver.shared.getPropertiesRepository()

        Task { [self] in
            do {
                let getPropertiesByType = try await asyncFunction(for: propertiesRepository.getPropertiesByTypeNative(type: .condo, offset: 0, limit: 20, sort: .init(field: .byprice, order: .ascending)))

                print("ContentViewModel::init() -> getPropertiesByType[count: \(getPropertiesByType.count)] = \(getPropertiesByType.map { $0.propertyID })")
                
                self.properties = getPropertiesByType
            } catch {
                print("ContentViewModel::init() -> err = \(error.localizedDescription)")
            }

        }
    }
    
}
