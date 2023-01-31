package devlcc.io.kmmshowcaserealestate.core.model.property

import devlcc.io.kmmshowcaserealestate.core.model.di.CommonParcelable
import devlcc.io.kmmshowcaserealestate.core.model.di.CommonParcelize

@CommonParcelize
data class Property(
    val propertyID: String? = null,
    val listingID: String? = null,

    val products: List<Product> = emptyList(),

    val rdcWebURL: String? = null,

    val propType: Type? = null,
    val propSubType: SubType? = null,
    val virtualTour: VirtualTour? = null,
    val address: Address? = null,
    val branding: Branding? = null,
    val propStatus: Status? = null,

    val price: Long? = null,

    val bathsFull: Int? = null,
    val baths: Int? = null,
    val beds: Int? = null,
    val buildingSize: BuildingSize? = null,
    val openHouses: List<OpenHouse> = emptyList(),

    val agents: List<Agent> = emptyList(),
    val office: Office? = null,

    val lastUpdate: String? = null, // ISODate (i.e. "2022-09-09T18:41:04Z")
    val clientDisplayFlags: ClientDisplayFlags? = null,

    val leadForms: LeadForms? = null,

    val photoCount: Int? = null,
    val thumbnail: String? = null,  // Image URL

    val pageNo: Int? = null,
    val rank: Long? = null,
    val listTracking: String? = null,   // "type|property|data|prop_id|3164652037|list_id|2941799499|page|rank|data_source|mls|advertiser_id|agent|office|property_status|product_code|advantage_code^1|Q|1OHG9|2DTUJ|PAC|G|5^^$0|1|2|$3|4|5|6|7|H|8|I|9|A|B|$C|J|D|K]|E|L|F|M|G|N]]"

    val mls: MultipleListingService? = null,
    val dataSourceName: MultipleListingService.Type? = null,

    ) : CommonParcelable {

    @CommonParcelize
    enum class Product : CommonParcelable {
        CoreAgent,  // "core.agent"
        CoreBroker, // "core.broker"
        CoBroke,    // "co_broke"
    }

    @CommonParcelize
    data class VirtualTour(
        val href: String? = null,
    ): CommonParcelable

    @CommonParcelize
    enum class Type : CommonParcelable {
        Condo,  // "condo"
        SingleFamily,  // "single_family"
        Land,  // "land"
        MultiFamily,  // "multi_family"
        Apartment,  // "apartment"

    }

    @CommonParcelize
    enum class SubType : CommonParcelable {
        Coop,  // "coop"
        Condos,  // "condos"
    }

    @CommonParcelize
    enum class Status : CommonParcelable {
        ForSale,  // "for_sale"
        ForRent,  // "for_rent"
        NotForSale,  // "not_for_sale"
    }

    @CommonParcelize
    data class Sort(
        val field: Sort.Field,
        val order: Sort.Order,
    ): CommonParcelable {

        @CommonParcelize
        enum class Field(val value: String): CommonParcelable {
            ByPropertyID("propertyID"),
            ByPrice("price"),
            ByLastUpdate("lastUpdate"),
            ByRank("rank"),
        }

        @CommonParcelize
        enum class Order: CommonParcelable {
            Ascending,
            Descending,
        }

        companion object {
            val Default = Sort(
                field = Field.ByPrice,
                order = Order.Descending
            )
        }

    }
}
