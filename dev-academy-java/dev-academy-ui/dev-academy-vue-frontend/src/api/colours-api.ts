import { http } from './http-common';

export async function getColours(): Promise<IColour[]> {
  const response = await http.get('colours');

  return response.data || [];
}
