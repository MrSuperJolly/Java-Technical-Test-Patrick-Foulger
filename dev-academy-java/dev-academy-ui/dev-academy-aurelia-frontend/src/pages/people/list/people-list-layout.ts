import { autoinject } from "aurelia-framework";
import { HttpClient } from "aurelia-fetch-client";
import { Person } from "models/person";

@autoinject
export class PeopleListLayout {
  constructor(private http: HttpClient) { }

  heading: string = "People";
  people: Person[] =[];

  async activate() {
    const response = await this.http.fetch("/people");
    const people = await response.json() as IPerson[];

    this.people = people.map(person => new Person(person));
  }
}
