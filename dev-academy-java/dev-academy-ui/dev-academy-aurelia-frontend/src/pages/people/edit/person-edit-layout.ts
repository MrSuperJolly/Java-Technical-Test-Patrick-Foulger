import { autoinject } from "aurelia-framework";
import { HttpClient } from "aurelia-fetch-client";
import { Router, RouteConfig } from "aurelia-router";
import { Person } from "models/person";

@autoinject
export class PersonEditLayout {
  constructor(private http: HttpClient, private router: Router) { }

  heading: string;
  person: Person;
  colourOptions: IColour[] = [];

  private routerConfig: RouteConfig;

  async activate(params, routerConfig: RouteConfig) {
    this.routerConfig = routerConfig;

    const personResponse = await this.http.fetch(`/people/${params.id}`);
    this.personFetched(await personResponse.json() as IPerson);

    const colourResponse = await this.http.fetch("/colours");
    this.colourOptions = await colourResponse.json() as IColour[];

    const favColours = this.person.colours.map(c => c.id);
    this.person.colours = this.colourOptions.filter(c => favColours.includes(c.id));
  }

  personFetched(person: IPerson) {
    this.person = new Person(person)

    this.heading = `Update ${this.person.fullName}`;
    this.routerConfig.navModel.setTitle(`Update ${this.person.fullName}`);
  }

  async submit() {
   // TODO: Step 7
        //
        // Implement the submit and save logic.
        // Send a JSON request to the API with the newly updated
        // this.person object. If the response is successful then
        // the user should be navigated to the list page.

        let error = false;

        this.http.fetch("http://localhost:5000/api/people/" + this.person.id.toString(), {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(this.person)
        })
            .then(response => response.json())
            .then(response => console.log(response))
            .catch(error => {
                console.error(error);
                error = true;
            });

      if (!error) {
          this.router.navigate('people');
        }
  }

  cancel() {
    this.router.navigate("people");
  }
}
