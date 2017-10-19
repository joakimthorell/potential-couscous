#converter from VCU strings to ints and the other way around
#test this sript by running the stringConverterTest script

def stringToVInt(vcuString):
  return int(vcuString[1:5])

def stringToHInt(vcuString):
  return int(vcuString[6:])

#the string should have the format vAXXX
#where A is 0 for posetive and - for negative
#where X is the number from 000 to 100
def intToVString(vInt):
  string = ""
  vInt = int(round(vInt, 0))
  if vInt < 0:
    if vInt > -10:
      string = "V-00" + str(vInt)[1:]
    elif vInt > -100:
      string = "V-0" + str(vInt)[1:]
    else:
      string = "V" + str(vInt)
  
  else: 
    if vInt < 10:
      string = "V000" + str(vInt)
    elif vInt < 100:
      string = "V00" + str(vInt)
    else:
      string = "V0100"
  return string

#the string should have the format hAXXX
#where A is 0 for posetive and - for negative
#where X is the number from 000 to 100
def intToHString(hInt):
  
  string = ""
  hInt = int(round(hInt, 0))
  if hInt < 0:
    if hInt > -10:
      string = "H-00" + str(hInt)[1:]
    elif hInt > -100:
      string = "H-0" + str(hInt)[1:]
    else:
      string = "H" + str(hInt)
  
  else: 
    if hInt < 10:
      string = "H000" + str(hInt)
    elif hInt < 100:
      string = "H00" + str(hInt)
    else:
      string = "H0100"
  return string


def intToTotalString(v, h):
  return intToVString(v) + intToHString(h)

def stringToTotalInt(vcuString):
  return [stringToVInt(vcuString), stringToHInt(vcuString)]
