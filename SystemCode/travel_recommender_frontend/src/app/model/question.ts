import { question_box } from './question_attr';


export const question: question_box[] = [
  {
    id:	0,
	question: "which country do you prefer?",
	hidden:	1,
	type:4,//1.checkbox 2.date 3.edit 4.dropbox 5.multiselect
	field:"qnsCountryId",
  },
  {
    id:	1,
	question: "your estimated departure date?",
	hidden:	1,
	type:2,//1.checkbox 2.date 3.edit 4.dropbox
	field:"qnsDepartureTime",

  },
  {
    id:	2,
	question: "your estimated leaving date?",
	hidden:	1,
	type:2,//1.checkbox 2.date 3.edit 4.dropbox
	field:"qnsLeavingTime",
  },
  {
    id:	3,
	question: "do you have high expectation for food?",
	hidden:	1,
	type:1,//1.checkbox 2.date 3.edit 4.dropbox
	field:"qnsFoodExpectation",
  },
  {
    id:	4,
	question: "do you want to experence legit local culture?",
	hidden:	1,
	type:1,//1.checkbox 2.date 3.edit 4.dropbox
	field:"qnsCultural",
  },
  {
    id:	5,
	question: "do you enjoy downtown places?",
	hidden:	1,
	type:1,//1.checkbox 2.date 3.edit 4.dropbox
	field:"qnsDowntown",
  },
  {
    id:	6,
	question: "can you mind crowded attractions when travelling?",
	hidden:	1,
	type:1,//1.checkbox 2.date 3.edit 4.dropbox
	field:"qnsCrowded",
  },
  {
    id:	7,
	question: "could you estimate your budget for the whole trip?",
	hidden:	1,
	type:3,//1.checkbox 2.date 3.edit 4.dropbox
	field:"qnsBudget",
  },
  {
    id:	8,
	question: "are you travelling with any kid or elder?",
	hidden:	1,
	type:1,//1.checkbox 2.date 3.edit 4.dropbox
	field:"qnsKidElder",
  },
  {
    id:	9,
	question: "do you plan to go shopping?",
	hidden:	1,
	type:1,//1.checkbox 2.date 3.edit 4.dropbox
	field:"qnsSouveniers",
  },
  {
    id:	10,
	question: "do you enjoy city skyline and outlook?",
	hidden:	1,
	type:1,//1.checkbox 2.date 3.edit 4.dropbox
	field:"qnsView",
  }
 //  {
 //    id:	11,
	// question: "Choose the place you like to visit.",
	// hidden:	1,
	// type:5,//1.checkbox 2.date 3.edit 4.dropbox
	// field:"Type",
 //  }
];



//@input 是父组件传类进子中，现在用不到