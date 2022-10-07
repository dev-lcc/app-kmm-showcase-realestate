//
//  PropertyDetailView.swift
//  iosApp
//
//  Created by Lawrence Cendana on 10/7/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import data

struct PropertyDetailView: View {
    
    var property: ModelProperty
    
    var body: some View {
        
        ScrollView {
            LazyVStack {
                // TODO::
                Text(
                    String(describing: property)
                        .replacingOccurrences(of: "(", with: "(\n")
                        .replacingOccurrences(of: ",", with: ",\n")
                        .replacingOccurrences(of: ")", with: ")\n")
                )
                    .padding(EdgeInsets(top: 24, leading: 8, bottom: 24, trailing: 8))
            }
        }
        .navigationTitle(getTitle(property.address))
        .navigationBarTitleDisplayMode(.inline)
        
    }
    
    private func getTitle(_ address: ModelAddress?) -> String {
        let address1 = address?.neighborhoodName ?? "???"
        let address2 = address?.city ?? "<Unknown City>"
        let address3 = address?.county ?? "<Unknown Country>"
        
        return "\(address1), \(address2), \(address3)"
    }
    
}

struct PropertyDetailView_Previews: PreviewProvider {
    static var previews: some View {
        PropertyDetailView(
            property: PropertyDetailView_Previews.SAMPLE_PROPERTY
        )
    }
    
    fileprivate static let SAMPLE_PROPERTY = ModelProperty(
        propertyID: "M9941116325",
        listingID: "2946805109",
        products: [.coreagent],
        rdcWebURL: nil,
        propType: .condo,
        propSubType: .condos,
        virtualTour: nil,
        address: ModelAddress(city: "Pasig", line: nil, postalCode: "1600", stateCode: nil, state: "Metro Manila", county: "Philippines", fipsCode: nil, countyNeededForUniq: nil, lat: nil, lon: nil, neighborhoodName: "Lumiere Residences", timeZone: "GMT+8"),
        branding: nil,
        propStatus: .forrent,
        price: KotlinLong(integerLiteral: 25000000),
        bathsFull: KotlinInt(int: 1),
        baths: KotlinInt(int: 1),
        beds: KotlinInt(int: 2),
        buildingSize: ModelBuildingSize(size: 49, units: "sqm"),
        openHouses: [],
        agents: [],
        office: nil,
        lastUpdate: "2022-09-09T18:41:04Z",
        clientDisplayFlags: nil,
        leadForms: nil,
        photoCount: KotlinInt(integerLiteral: 14),
        thumbnail: nil,
        pageNo: nil,
        rank: nil,
        listTracking: nil,
        mls: nil,
        dataSourceName: .mls
    )
}
