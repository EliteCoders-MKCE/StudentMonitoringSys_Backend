const MODEL_URL = './models'
Promise.all([
    faceapi.nets.faceRecognitionNet.loadFromUri(MODEL_URL),
    faceapi.nets.faceLandmark68Net.loadFromUri(MODEL_URL),
    faceapi.nets.ssdMobilenetv1.loadFromUri(MODEL_URL)
]).then(console.log('Models Loaded'));

let webcam = null;
let imgSet = [null,null,null]


function loadLabeledImages(imgSet,name) {
    if(!faceapi){
        alert('ML models not loaded')
        window.location.reload()
    }
    const labels = [name] // for Label OP
     return Promise.all(
         labels.map(async (label)=>{
             const descriptions = []
             for(let i=1; i<=3; i++) {
                 document.getElementById('temp-img').src = imgSet[i-1]
                 const img = document.getElementById('temp-img')
                 const detections = await faceapi.detectSingleFace(img).withFaceLandmarks().withFaceDescriptor()
                 console.log(label + i + JSON.stringify(detections))
                 descriptions.push(detections.descriptor)
             }
             return new faceapi.LabeledFaceDescriptors(label, descriptions)
         })
     ).then(data=>{
        /*console.log(data.constructor.name)
        localStorage.setItem('temp_data',JSON.stringify(data))
         let transdata = {
            'label' : data[0].label,
            'descriptors' : data[0].descriptors
         }
         console.log(data[0].descriptors)
         console.log(data[0].label)
         console.log(transdata)*/
        let regno =  document.getElementById('reg-reg-no').value;
       //  console.log(JSON.stringify("\n\n\nStart\n"+JSON.stringify(data)+"\n\nFrom promise.."))
        let xhr = new XMLHttpRequest();
        
        let url = 'http://localhost:8080/'+'api/misc/store-model?register_no='+regno;
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Content-Type", "text/plain");
        xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            alert(this.responseText);
            //document.getElementById("add-atn-modal").style.display="none";
            //window.location.reload();
        }
        };
        var jdata = JSON.stringify(data);
        xhr.send(jdata);
     })
 } 

 $( document ).ready(function() {
            const webcamElement = document.getElementById('webcam');
            const canvasElement = document.getElementById('canvas');
            const snapSoundElement = document.getElementById('snapSound');
            webcam = new Webcam(webcamElement, 'user', canvasElement, snapSoundElement);
            webcam.start()
            .then(result =>{
                console.log("webcam started");
            })
            .catch(err => {
                console.log(err);
            });
        });

 function capture(){
            if(imgSet[0]==null)
                imgSet[0] = webcam.snap()
            else if(imgSet[1]==null)
                imgSet[1] = webcam.snap()
            else if(imgSet[2]==null)
                imgSet[2] = webcam.snap()

            console.log(imgSet)
            if(imgSet[0]!=null && imgSet[1]!=null && imgSet[2]!=null)
            {
                document.getElementById('capture-btn').innerText='Done';
                document.getElementById('capture-btn').disabled=true; 
                document.getElementById('capture-btn').classList.add('btn-warning');
                let regno =  document.getElementById('reg-reg-no').value;
                loadLabeledImages(imgSet,regno);
            } 
               
        }


