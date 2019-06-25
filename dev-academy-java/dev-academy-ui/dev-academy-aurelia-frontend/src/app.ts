import { Router, RouterConfiguration } from "aurelia-router";
import { PLATFORM } from "aurelia-pal";

export class App {
  private router: Router;

  configureRouter(config: RouterConfiguration, router: Router) {
    config.title = "Colours Test";

    config.map([
      { route: "", redirect: "/people" },
      { route: "people", name: "people", moduleId: PLATFORM.moduleName("pages/people/list/people-list-layout"), title: "People" },
      { route: "people/:id", name: "person", moduleId: PLATFORM.moduleName("pages/people/edit/person-edit-layout"), title: "Update" }
    ]);
  }
}
