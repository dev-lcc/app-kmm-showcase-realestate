import SwiftUI
import model

struct ContentView: View {
    let property = Property(
        propertyID: "M9941116325",
        listingID: "2946805109",
        products: [.coreagent],
        rdcWebURL: nil,
        propType: .condo,
        propSubType: .condos,
        virtualTour: nil,
        address: nil,
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
    )

	var body: some View {
		Text(
            String(describing: property)
                .replacingOccurrences(of: "(", with: "(\n")
                .replacingOccurrences(of: ",", with: ",\n")
                .replacingOccurrences(of: ")", with: ")\n")
        )
            .padding(EdgeInsets(top: 24, leading: 8, bottom: 24, trailing: 8))
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
