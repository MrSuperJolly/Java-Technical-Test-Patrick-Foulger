export class ColourNamesValueConverter {
  toView(colours: IColour[]) {

    // TODO: Step 4
    //
    // Implement the value converter function.
    // Using the colours parameter, convert the list into a comma
    // separated string of colour names. The names should be sorted
    // alphabetically and there should not be a trailing comma.
    //
    // Example: "Blue, Green, Red"

    let sortedColours = [];
    colours.forEach(function (Item) {
        sortedColours.push(Item.name);
    });

    return sortedColours.sort().join(', ');
  }
}
