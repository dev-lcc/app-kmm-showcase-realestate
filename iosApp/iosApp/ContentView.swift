import SwiftUI
import KMMShowcaseKit
import KMMViewModelCore
import KMMViewModelSwiftUI
import Combine

struct ContentView: View {
    @StateViewModel var viewModel: ListingsViewModel
    
	var body: some View {
        NavigationView {
            let allProperties = viewModel.uiState.propertiesForRent + viewModel.uiState.propertiesForSale + viewModel.uiState.propertiesSold
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
                self.viewModel.refresh()
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

//struct ContentView_Previews: PreviewProvider {
//    private static let content = {
//        let _view = ContentView()
//        return _view
//    }()
//
//	static var previews: some View {
//        content
//            .onAppear {
//                // content.contentViewModel.properties = SAMPLE_PROPERTIES
//            }
//	}
//
//}
//
//fileprivate let SAMPLE_PROPERTIES = [
//    Property(
//        propertyID: "M9941116325",
//        listingID: "2946805109",
//        products: [.coreagent],
//        rdcWebURL: nil,
//        propType: .condo,
//        propSubType: .condos,
//        virtualTour: nil,
//        address: Address(city: "Pasig", line: nil, postalCode: "1600", stateCode: nil, state: "Metro Manila", county: "Philippines", fipsCode: nil, countyNeededForUniq: nil, lat: nil, lon: nil, neighborhoodName: "Lumiere Residences", timeZone: "GMT+8"),
//        branding: nil,
//        propStatus: .forrent,
//        price: KotlinLong(integerLiteral: 25000000),
//        bathsFull: KotlinInt(int: 1),
//        baths: KotlinInt(int: 1),
//        beds: KotlinInt(int: 2),
//        buildingSize: BuildingSize(size: 49, units: "sqm"),
//        openHouses: [],
//        agents: [],
//        office: nil,
//        lastUpdate: "2022-09-09T18:41:04Z",
//        clientDisplayFlags: nil,
//        leadForms: nil,
//        photoCount: KotlinInt(integerLiteral: 14),
//        thumbnail: nil,
//        pageNo: nil,
//        rank: nil,
//        listTracking: nil,
//        mls: nil,
//        dataSourceName: .mls
//    )
//]

