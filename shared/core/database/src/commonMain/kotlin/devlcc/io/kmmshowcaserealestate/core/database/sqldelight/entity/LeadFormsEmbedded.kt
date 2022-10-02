package devlcc.io.kmmshowcaserealestate.core.database.sqldelight.entity

import devlcc.io.kmmshowcaserealestate.core.model.property.LeadForms

fun Embedded.LeadForms.toModel(): LeadForms = LeadForms(
    form = this.form?.toModel(),
    showAgent = this.showAgent,
    showBroker = this.showBroker,
    showBuilder = this.showBuilder,
    showContactALender = this.showContactALender,
    showVeteransUnited = this.showVeteransUnited,
    formType = this.formType?.let { embedded ->
        when (embedded) {
            Embedded.LeadForms.FormType.Classic -> LeadForms.FormType.Classic
        }
    },
    leadType = this.leadType?.let { embedded ->
        when (embedded) {
            Embedded.LeadForms.LeadType.CoBroke -> LeadForms.LeadType.CoBroke
        }
    },
    isLcmEnabled = this.isLcmEnabled,
    flipTheMarketEnabled = this.flipTheMarketEnabled,
    localPhone = this.localPhone,
    localPhones = this.localPhones?.let { embedded ->
        LeadForms.LocalPhones(
            commConsoleMweb = embedded.commConsoleMweb,
        )
    },
    showTextLeads = this.showTextLeads,
    cashbackEnabled = this.cashbackEnabled,
    smarthomeEnabled = this.smarthomeEnabled,
)

fun Embedded.LeadForms.Fields.toModel(): LeadForms.Fields = LeadForms.Fields(
    name = this.name?.toModel(),
    email = this.email?.toModel(),
    phone = this.phone?.toModel(),
    message = this.message?.toModel(),
    show = this.show,
)

fun Embedded.LeadForms.Fields.Validation.toModel(): LeadForms.Fields.Validation =
    LeadForms.Fields.Validation(
        required = this.required,
        minimumCharacterCount = this.minimumCharacterCount,
        maximumCharacterCount = this.maximumCharacterCount,
    )

fun LeadForms.toEmbedded(): Embedded.LeadForms = Embedded.LeadForms(
    form = this.form?.toEmbedded(),
    showAgent = this.showAgent,
    showBroker = this.showBroker,
    showBuilder = this.showBuilder,
    showContactALender = this.showContactALender,
    showVeteransUnited = this.showVeteransUnited,
    formType = this.formType?.let {
        when (it) {
            LeadForms.FormType.Classic -> Embedded.LeadForms.FormType.Classic
        }
    },
    leadType = this.leadType?.let {
        when (it) {
            LeadForms.LeadType.CoBroke -> Embedded.LeadForms.LeadType.CoBroke
        }
    },
    isLcmEnabled = this.isLcmEnabled,
    flipTheMarketEnabled = this.flipTheMarketEnabled,
    localPhone = this.localPhone,
    localPhones = this.localPhones?.let {
        Embedded.LeadForms.LocalPhones(
            commConsoleMweb = it.commConsoleMweb,
        )
    },
    showTextLeads = this.showTextLeads,
    cashbackEnabled = this.cashbackEnabled,
    smarthomeEnabled = this.smarthomeEnabled,
)

fun LeadForms.Fields.toEmbedded(): Embedded.LeadForms.Fields = Embedded.LeadForms.Fields(
    name = this.name?.toEmbedded(),
    email = this.email?.toEmbedded(),
    phone = this.phone?.toEmbedded(),
    message = this.message?.toEmbedded(),
    show = this.show,
)

fun LeadForms.Fields.Validation.toEmbedded(): Embedded.LeadForms.Fields.Validation =
    Embedded.LeadForms.Fields.Validation(
        required = this.required,
        minimumCharacterCount = this.minimumCharacterCount,
        maximumCharacterCount = this.maximumCharacterCount,
    )
