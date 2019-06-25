import "core-js/stable";
import { HttpClient } from "aurelia-fetch-client";
import { Aurelia } from "aurelia-framework";
import { PLATFORM } from "aurelia-pal";

import environment from "./environment";

import "./style.scss";

export function configure(aurelia: Aurelia) {
  const http = new HttpClient();
  http.configure(config => {
    config
      .useStandardConfiguration()
      .withDefaults({ headers: { "Accept": "application/json" } })
      .withBaseUrl("http://localhost:5000/api");
  });

  aurelia.use
    .instance(HttpClient, http)
    .standardConfiguration()
    .globalResources(PLATFORM.moduleName("shared/colour-names-value-converter"))
    .developmentLogging(environment.debug ? "debug" : "warn");

  if (environment.testing) {
    aurelia.use.plugin(PLATFORM.moduleName("aurelia-testing"));
  }

  aurelia.start().then(() => aurelia.setRoot(PLATFORM.moduleName("app")));
}
