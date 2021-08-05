package com.justai.jaicf.template.scenario

import com.justai.jaicf.builder.Scenario

val mainScenario = Scenario {
    state("start") {
        activators {
            regex("/start")
        }
        action {
            reactions.go("modal")
        }

        state("modal", modal = true) {
            activators {
                event("modal-event")
            }

            action {
                reactions.say("i'm in modal")

            }
        }
    }
}