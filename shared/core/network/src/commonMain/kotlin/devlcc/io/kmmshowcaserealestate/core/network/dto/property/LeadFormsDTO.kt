package devlcc.io.kmmshowcaserealestate.core.network.dto.property

import devlcc.io.kmmshowcaserealestate.core.model.property.LeadForms
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class LeadFormsDTO(
    val form: Fields? = null,

    @SerialName("show_agent")
    val showAgent: Boolean? = null,

    @SerialName("show_broker")
    val showBroker: Boolean? = null,

    @SerialName("show_builder")
    val showBuilder: Boolean? = null,

    @SerialName("show_contact_a_lender")
    val showContactALender: Boolean? = null,

    @SerialName("show_veterans_united")
    val showVeteransUnited: Boolean? = null,

    @SerialName("form_type")
    val formType: String? = null,

    @SerialName("lead_type")
    val leadType: String? = null,

    @SerialName("is_lcm_enabled")
    val isLcmEnabled: Boolean? = null,

    @SerialName("flip_the_market_enabled")
    val flipTheMarketEnabled: Boolean? = null,

    @SerialName("local_phone")
    val localPhone: String? = null,

    @SerialName("local_phones")
    val localPhones: LocalPhones? = null,

    @SerialName("show_text_leads")
    val showTextLeads: Boolean? = null,

    @SerialName("cashback_enabled")
    val cashbackEnabled: Boolean? = null,

    @SerialName("smarthome_enabled")
    val smarthomeEnabled: Boolean? = null,
) {

    @Serializable
    data class Fields(
        val name: Validation? = null,
        val email: Validation? = null,
        val phone: Validation? = null,
        val message: Validation? = null,
        val show: Boolean? = null,
    ) {

        @Serializable
        data class Validation(
            val required: Boolean? = null,
            @SerialName("minimum_character_count")
            val minimumCharacterCount: Int? = null,   // "minimum_character_count"
            @SerialName("maximum_character_count")
            val maximumCharacterCount: Int? = null
        )

    }

    @Serializable
    data class LocalPhones(
        @SerialName("comm_console_mweb")
        val commConsoleMweb: String? = null,  // comm_console_mweb (i.e. "(888)860-0947")
    )

}

fun LeadFormsDTO.toModel(): LeadForms =
    LeadForms(
        form = this.form?.toModel(),
        showAgent = this.showAgent,
        showBroker = this.showBroker,
        showBuilder = this.showBuilder,
        showContactALender = this.showContactALender,
        showVeteransUnited = this.showVeteransUnited,
        formType = this.formType?.let { str ->
            when(str) {
                "classic" -> LeadForms.FormType.Classic
                else -> null
            }
        },
        leadType = this.leadType?.let { str ->
            when(str) {
                "co_broke" -> LeadForms.LeadType.CoBroke
                else -> null
            }
        },
        isLcmEnabled = this.isLcmEnabled,
        flipTheMarketEnabled = this.flipTheMarketEnabled,
        localPhone = this.localPhone,
        showTextLeads = this.showTextLeads,
        cashbackEnabled = this.cashbackEnabled,
        smarthomeEnabled = this.smarthomeEnabled,
    )

fun LeadFormsDTO.Fields.toModel(): LeadForms.Fields =
    LeadForms.Fields(
        name = this.name?.toModel(),
        email = this.email?.toModel(),
        phone = this.phone?.toModel(),
        message = this.message?.toModel(),
        show = this.show,
    )

fun LeadFormsDTO.Fields.Validation.toModel(): LeadForms.Fields.Validation =
    LeadForms.Fields.Validation(
        required = this.required,
        minimumCharacterCount = this.minimumCharacterCount,
        maximumCharacterCount = this.maximumCharacterCount,
    )

fun LeadFormsDTO.LocalPhones.toModel(): LeadForms.LocalPhones =
    LeadForms.LocalPhones(
        commConsoleMweb = this.commConsoleMweb
    )
