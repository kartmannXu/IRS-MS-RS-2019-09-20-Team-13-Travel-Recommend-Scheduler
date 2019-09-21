export class Answer {
	id:number;
    type = 0;//1.checkbox 2.date 3.edit 4.dropbox
    edit:number;//输入
    select : boolean;//选择
    combobox : string;//下拉列表
    date : string;
}