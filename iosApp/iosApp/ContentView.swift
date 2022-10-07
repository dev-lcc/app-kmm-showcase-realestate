import SwiftUI
import data
import KMPNativeCoroutinesAsync
import KMPNativeCoroutinesCore
import Combine

struct ContentView: View {
    @StateObject var viewModel: ContentViewModel = ContentViewModel()

	var body: some View {
        NavigationView {
            List(viewModel.properties, id: \.propertyID) { item in
                NavigationLink(destination: {
                    PropertyDetailView(property: item)
                }, label: {
                    Text(getLabel(item.address))
                })
                .listStyle(.automatic)
            }
            .navigationTitle("KMM Real Estate")
            
        }
        
	}
    
    private func getLabel(_ address: ModelAddress?) -> String {
        let address1 = address?.neighborhoodName ?? "???"
        let address2 = address?.city ?? "<Unknown City>"
        let address3 = address?.county ?? "<Unknown Country>"
        
        return "\(address1), \(address2), \(address3)"
    }
}

struct ContentView_Previews: PreviewProvider {
    private static let content = {
        let _view = ContentView()
        return _view
    }()
    
	static var previews: some View {
        content
            .onAppear {
                content.viewModel.properties = SAMPLE_PROPERTIES
            }
	}
    
    fileprivate static let SAMPLE_PROPERTIES = [
        ModelProperty(
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
    ]
}
