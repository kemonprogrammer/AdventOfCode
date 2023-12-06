const getNum = (lineArr, textNums)=>{
	let indices = textNums.reduce(((obj, text)=>{
		obj[text] = -1
		return obj;
	}), {})
  outer:for(let i = 0; i< lineArr.length;i++){
		const char = lineArr[i]
    if (Number(char).toString() !== "NaN" ){
			return Number(char)
		}
		for (text of textNums){
			const oldIndex = indices[text]
			if (char === text[oldIndex+1]){
				indices[text] = oldIndex + 1
				if (indices[text] === text.length - 1){
					return textNums.findIndex(t => t === text) + 1
				}
			} else if (char === text[0]) {
				indices[text] = 0
			} 
// 			else if (text === "eerht"){
// 				if (char === text[1]) { indices[text] = 1}
// 			} 
			else {
				indices[text] = -1
			}
		}
		
  }
// 	console.log(`indices`)
// 	console.log(indices)
}

document.body.textContent.split("\n").filter(Boolean).map((line,lineNum,arr) => {
	let lineArr = line.split('')
	let firstNum = getNum(lineArr, textNums)
	let lastNum= getNum(lineArr.toReversed(), revTextNums)
	const result = getConcNum(firstNum,lastNum)

// 	if ([2,3,4,5,6,7,8, arr.length-1].includes(lineNum) ){
// 		console.log('FOR : ' + lineNum)
// 		console.log(`line: ${line} first: ${firstNum} last: ${lastNum} result: ${result}`)
// 	}

	return result
}).reduce(((acc, num, i)=> acc + num), 0)

