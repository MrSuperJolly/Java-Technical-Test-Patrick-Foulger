import { http } from './http-common';

export async function getPeople(): Promise<IPerson[]> {
  const response = await http.get('people');

  return response.data || [];
}

export async function getPerson(id: string): Promise<IPerson> {
  const response = await http.get(`people/${id}`);

  return response.data;
}

export async function updatePerson(person: IPerson): Promise<boolean> {
  // TODO: Step 7
  //
  // Implement the submit and save logic.
  // Send a JSON request to the API with the newly updated
  // person object. Return true or false based if the response
  // is a success or error.

  throw new Error('Not Implemented');
}
