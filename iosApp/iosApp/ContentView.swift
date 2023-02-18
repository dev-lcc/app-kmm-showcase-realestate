import SwiftUI
import KMMShowcaseKit
import KMMViewModelCore
import KMMViewModelSwiftUI
import Combine

struct ContentView: View {
    @StateViewModel var viewModel: ListingsViewModel
    
    var body: some View {
        _ContentView(
            uiState: viewModel.uiState,
            onRefresh: {
                self.viewModel.refresh()
            }
        )
    }
}

fileprivate struct _ContentView: View {
    var uiState: ListingsUiState
    var onRefresh: (() -> Void)
    
    var body: some View {
        NavigationView {
            let allProperties = uiState.propertiesForRent + uiState.propertiesForSale + uiState.propertiesSold
            List(allProperties, id: \.self) { item in
                NavigationLink(destination: {
                    PropertyDetailView(property: item)
                }, label: {
                    Text(getLabel(item.address))
                })
                .listStyle(.automatic)
            }
            .refreshable(action: {
                print("ContentView::refreshable()")
                self.onRefresh()
            })
            .navigationTitle("KMM Real Estate")
        }
    }
    
    private func getLabel(_ address: Address?) -> String {
        let address1 = address?.neighborhoodName ?? "???"
        let address2 = address?.city ?? "<Unknown City>"
        let address3 = address?.county ?? "<Unknown Country>"
        
        return "\(address1), \(address2), \(address3)"
    }
    
}

struct ContentView_Previews: PreviewProvider {
    private static let content = {
        let _view = _ContentView(
            uiState: ListingsUiState(
                isLoading: false,
                propertiesForSale: SAMPLE_PROPERTIES.filter { $0.propStatus == .forrent },
                propertiesForRent: SAMPLE_PROPERTIES.filter { $0.propStatus == .forsale },
                propertiesSold: SAMPLE_PROPERTIES.filter { $0.propStatus == .notforsale }
            ),
            onRefresh: {}
        )
        return _view
    }()

	static var previews: some View {
        content
	}

}

fileprivate let SAMPLE_PROPERTIES = [
    Property(
        propertyID: "M9941116325",
        listingID: "2946805109",
        products: [.coreagent],
        rdcWebURL: nil,
        propType: .condo,
        propSubType: .condos,
        virtualTour: nil,
        address: Address(city: "Pasig", line: nil, postalCode: "1600", stateCode: nil, state: "Metro Manila", county: "Philippines", fipsCode: nil, countyNeededForUniq: nil, lat: nil, lon: nil, neighborhoodName: "Lumiere Residences", timeZone: "GMT+8"),
        branding: nil,
        propStatus: .forrent,
        price: KotlinLong(integerLiteral: 25000000),
        bathsFull: KotlinInt(int: 1),
        baths: KotlinInt(int: 1),
        beds: KotlinInt(int: 2),
        buildingSize: BuildingSize(size: 49, units: "sqm"),
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
    ),
    Property(
        propertyID: "M9941116326",
        listingID: "2946805110",
        products: [.coreagent],
        rdcWebURL: nil,
        propType: .condo,
        propSubType: .condos,
        virtualTour: nil,
        address: Address(city: "Makati", line: nil, postalCode: "1620", stateCode: nil, state: "Metro Manila", county: "Philippines", fipsCode: nil, countyNeededForUniq: nil, lat: nil, lon: nil, neighborhoodName: "Rockwell Residences", timeZone: "GMT+8"),
        branding: nil,
        propStatus: .forrent,
        price: KotlinLong(integerLiteral: 36000000),
        bathsFull: KotlinInt(int: 1),
        baths: KotlinInt(int: 1),
        beds: KotlinInt(int: 2),
        buildingSize: BuildingSize(size: 53, units: "sqm"),
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
    ),
    Property(
        propertyID: "M9941116327",
        listingID: "2946805111",
        products: [.coreagent],
        rdcWebURL: nil,
        propType: .apartment,
        propSubType: .coop,
        virtualTour: nil,
        address: Address(city: "Mandaluyong", line: nil, postalCode: "1612", stateCode: nil, state: "Metro Manila", county: "Philippines", fipsCode: nil, countyNeededForUniq: nil, lat: nil, lon: nil, neighborhoodName: "JCL Appartments", timeZone: "GMT+8"),
        branding: nil,
        propStatus: .forsale,
        price: KotlinLong(integerLiteral: 50000000),
        bathsFull: KotlinInt(int: 1),
        baths: KotlinInt(int: 1),
        beds: KotlinInt(int: 2),
        buildingSize: BuildingSize(size: 480, units: "sqm"),
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
    ),
    Property(
        propertyID: "M9941116328",
        listingID: "2946805113",
        products: [.coreagent],
        rdcWebURL: nil,
        propType: .condo,
        propSubType: .condos,
        virtualTour: nil,
        address: Address(city: "Taguig", line: nil, postalCode: "1600", stateCode: nil, state: "Metro Manila", county: "Philippines", fipsCode: nil, countyNeededForUniq: nil, lat: nil, lon: nil, neighborhoodName: "Serendra Condominium", timeZone: "GMT+8"),
        branding: nil,
        propStatus: .forrent,
        price: KotlinLong(integerLiteral: 17000000),
        bathsFull: KotlinInt(int: 1),
        baths: KotlinInt(int: 1),
        beds: KotlinInt(int: 2),
        buildingSize: BuildingSize(size: 34, units: "sqm"),
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
    ),
]

