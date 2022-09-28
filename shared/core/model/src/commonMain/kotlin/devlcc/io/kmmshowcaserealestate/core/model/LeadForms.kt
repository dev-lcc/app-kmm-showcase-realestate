package devlcc.io.kmmshowcaserealestate.core.model

@CommonParcelize
data class LeadForms(
    val form: Fields? = null,
    val showAgent: Boolean,
    val showBroker: Boolean,
    val showBuilder: Boolean,
    val showContactALender: Boolean,
    val showVeteransUnited: Boolean,
    val formType: FormType? = null,
    val leadType: LeadType? = null,
    val isLcmEnabled: Boolean? = null,
    val flipTheMarketEnabled: Boolean? = null,
    val localPhone: String? = null,
    val localPhones: LocalPhones? = null,
    val showTextLeads: Boolean? = null,
    val cashbackEnabled: Boolean? = null,
    val smarthomeEnabled: Boolean? = null
) : CommonParcelable {

    @CommonParcelize
    data class Fields(
        val name: Validation? = null,
        val email: Validation? = null,
        val phone: Validation? = null,
        val message: Validation? = null,
        val show: Boolean? = null,
    ) : CommonParcelable {

        @CommonParcelize
        data class Validation(
            val required: Boolean? = null,
            val minimumCharacterCount: Int? = null,   // "minimum_character_count"
        ) : CommonParcelable

    }

    @CommonParcelize
    enum class FormType : CommonParcelable {
        Classic
    }

    @CommonParcelize
    enum class LeadType : CommonParcelable {
        CoBroke // co_broke
    }

    @CommonParcelize
    data class LocalPhones(
        val commConsoleMweb: String? = null,  // comm_console_mweb (i.e. "(888)860-0947")
    ) : CommonParcelable

}
