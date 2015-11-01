public static string SingleNumberToArabicName(int number)
        {
            switch (number)
            {
                case 1:
                    {
                        return ("احد");

                    }
                case 2:
                    {
                        return ("اثنان");
                    }
                case 3:
                    {
                        return ("ثلاث");
                    }
                case 4:
                    {
                        return ("اربع");
                    }
                case 5:
                    {
                        return ("خمس");
                    }
                case 6:
                    {
                        return ("ست");
                    }
                case 7:
                    {
                        return ("سبع");
                    }
                case 8:
                    {
                        return ("ثمان");
                    }
                case 9:
                    {
                        return ("تسعة");
                    }
                case 10:
                    {
                        return ("عشرة");
                    }
                case 20:
                    {
                        return ("عشرون");
                    }
                case 30:
                    {
                        return ("ثلاثون");
                    }
                case 40:
                    {
                        return ("اربعون");
                    }
                case 50:
                    {
                        return ("خمسون");
                    }
                case 60:
                    {
                        return ("ستون");
                    }
                case 70:
                    {
                        return ("سبعون");
                    }
                case 80:
                    {
                        return ("ثمانون");
                    }
                case 90:
                    {
                        return ("تسعون");
                    }
                case 100:
                    {
                        return ("مائة");
                    }
                case 200:
                    {
                        return ("مائتان");
                    }
                case 1000:
                    {
                        return ("الف");
                    }
                case 2000:
                    {
                        return ("الفان");
                    }
                case 3000:
                    {
                        return ("ثلاثة الاف");
                    }
                case 4000:
                    {
                        return ("اربعة الاف");
                    }
                case 5000:
                    {
                        return ("خمسة الاف");
                    }
                case 6000:
                    {
                        return ("ستة الاف");
                    }
                case 7000:
                    {
                        return ("سبعة الاف");
                    }
                case 8000:
                    {
                        return ("ثمانية الاف");
                    }
                case 9000:
                    {
                        return ("تسعة الاف");
                    }
                case 10000:
                    {
                        return ("عشرة الاف");
                    }
            }
            return ("");
        }


        /// <summary>
        /// Accept A Number As A String And Returns It's Value in Arabic As Text
        /// </summary>
        /// <param name="number">Number in String Format</param>
        /// <returns></returns>
        public string ConvertNumberToArabicString(decimal Mynumber, string currency, string smallChangeCurrency)
        {

            string myrturnString = "";
            string number = Mynumber.ToString();
            string numberBeforeDot = "";
            string numberAfterDot = "";
          
            if (number.Contains("."))
            {
                numberBeforeDot = number.Substring(0, number.IndexOf("."));
                try
                {
                    numberAfterDot = number.Substring(number.IndexOf(".") + 1, 2);
                }
                catch (Exception)
                {
                    numberAfterDot = number.Substring(number.IndexOf(".") + 1);
                }
            }
            else
            {
                numberBeforeDot = number;
            }
            int lenghtofNumberBeforeDt = numberBeforeDot.Length;
            int lenghtofNumberAfterDt = numberAfterDot.Length;

            #region Before .
            switch (lenghtofNumberBeforeDt)
            {
                case 1:
                    {
                        myrturnString += SingleNumberToArabicName(int.Parse(numberBeforeDot));
                        break;
                    }
                case 2:
                    {
                        string tmp = "";
                        string n1 = numberBeforeDot.Substring(1, 1);
                        tmp += SingleNumberToArabicName((int.Parse(n1)));
                        if (numberBeforeDot.Substring(0, 1) != "1")
                        {
                            if (numberBeforeDot.Substring(0, 1) != "0")
                            {
                                tmp += " و ";
                            }
                            string n2 = numberBeforeDot.Substring(0, 1);
                            tmp += SingleNumberToArabicName((int.Parse((n2 + '0'))));
                        }
                        else
                        {
                            tmp += "  ";
                            tmp += SingleNumberToArabicName(10);

                        }
                        myrturnString += tmp;
                        break;
                    }
                case 3:
                    {
                        string tmp = "";
                        string n1 = numberBeforeDot.Substring(0, 1);
                        if (n1 == "1")
                        {
                            tmp += SingleNumberToArabicName(100);
                        }
                        else if (n1 == "2")
                        {
                            tmp += SingleNumberToArabicName(200);
                        }

                        else
                        {
                            tmp += SingleNumberToArabicName(int.Parse(n1)) + " " + SingleNumberToArabicName(100);
                        }
                        tmp += " ";


                        if (numberBeforeDot.Substring(1, 1) != "1" && numberBeforeDot.Substring(1, 1) != "0")
                        {
                            string n2 = numberBeforeDot.Substring(2, 1);
                            tmp += SingleNumberToArabicName((int.Parse(n2)));
                            tmp += " و ";
                            string n3 = numberBeforeDot.Substring(1, 1);
                            tmp += SingleNumberToArabicName((int.Parse((n3 + '0'))));
                        }
                        else
                        {
                            if (numberBeforeDot.Substring(1, 1) == "0")
                            {
                                tmp += " و ";
                                string n2 = numberBeforeDot.Substring(2, 1);
                                tmp += SingleNumberToArabicName((int.Parse(n2)));
                            }
                            else
                            {
                                string n2 = numberBeforeDot.Substring(2, 1);
                                tmp += SingleNumberToArabicName((int.Parse(n2)));
                                tmp += "  ";
                                tmp += SingleNumberToArabicName(10);
                            }

                        }
                        myrturnString += tmp;
                        break;
                    }
                case 4:
                    {

                        string tmp = "";
                        string n0 = numberBeforeDot.Substring(0, 1);

                        if (n0 == "1")
                        {
                            tmp += SingleNumberToArabicName(1000);
                        }
                        else if (n0 == "2")
                        {
                            tmp += SingleNumberToArabicName(2000);
                        }
                        else
                        {
                            tmp += SingleNumberToArabicName(int.Parse(n0)) + " " + SingleNumberToArabicName(1000);
                        }

                        string n1 = numberBeforeDot.Substring(1, 1);
                        if (n1 == "1")
                        {
                            tmp += " و ";
                            tmp += SingleNumberToArabicName(100);
                        }
                        else if (n1 == "2")
                        {
                            tmp += " و ";
                            tmp += SingleNumberToArabicName(200);
                        }
                        else
                        {
                            if (n1 != "0")
                            {
                                tmp += " و ";
                                tmp += SingleNumberToArabicName(int.Parse(n1)) + " " + SingleNumberToArabicName(100);
                            }
                            else
                            {
                                if (numberBeforeDot.Substring(3, 1) != "0")
                                    tmp += " و ";
                            }
                        }
                        tmp += " ";

                        if (numberBeforeDot.Substring(2, 1) != "1" && numberBeforeDot.Substring(2, 1) != "0")
                        {
                            string n2 = numberBeforeDot.Substring(3, 1);
                            tmp += SingleNumberToArabicName((int.Parse(n2)));
                            tmp += " و ";
                            string n3 = numberBeforeDot.Substring(2, 1);
                            tmp += SingleNumberToArabicName((int.Parse((n3 + '0'))));
                        }
                        else
                        {
                            if (numberBeforeDot.Substring(2, 1) == "0")
                            {
                                if (numberBeforeDot.Substring(3, 1) != "0")
                                {
                                    string n2 = numberBeforeDot.Substring(3, 1);
                                    tmp += SingleNumberToArabicName((int.Parse(n2)));
                                }
                            }
                            else
                            {
                                string n2 = numberBeforeDot.Substring(3, 1);
                                tmp += SingleNumberToArabicName((int.Parse(n2)));
                                tmp += "  ";
                                tmp += SingleNumberToArabicName(10);
                            }

                        }
                        myrturnString += tmp;
                        break;
                    }
                case 5:
                    {
                        string tmp = "";

                        string firstTwo = numberBeforeDot.Substring(0, 2);
                        if (firstTwo == "10")
                        {
                            tmp += SingleNumberToArabicName(10000);
                        }
                        else
                        {
                            string n8 = firstTwo.Substring(1, 1);
                            tmp += SingleNumberToArabicName((int.Parse(n8)));

                            if (firstTwo.Substring(0, 1) != "1")
                            {
                                if (string.IsNullOrEmpty(tmp))
                                {
                                    string n2 = firstTwo.Substring(0, 1);
                                    tmp += SingleNumberToArabicName((int.Parse((n2 + '0'))));
                                }
                                else
                                {
                                    tmp += " و ";
                                    string n2 = firstTwo.Substring(0, 1);
                                    tmp += SingleNumberToArabicName((int.Parse((n2 + '0'))));
                                }
                            }
                            else
                            {
                                tmp += "  ";
                                tmp += SingleNumberToArabicName(10);

                            }
                            tmp += " " + SingleNumberToArabicName(1000);
                        }
                        tmp += " ";


                        string n1 = numberBeforeDot.Substring(2, 1);
                        if (n1 == "1")
                        {
                            tmp += " و ";
                            tmp += SingleNumberToArabicName(100);
                        }
                        else if (n1 == "2")
                        {
                            tmp += " و ";
                            tmp += SingleNumberToArabicName(200);
                        }
                        else
                        {
                            if (n1 != "0")
                            {
                                tmp += " و ";
                                tmp += SingleNumberToArabicName(int.Parse(n1)) + " " + SingleNumberToArabicName(100);
                            }
                            else
                            {
                                if (numberBeforeDot.Substring(4, 1) != "0")
                                    tmp += " و ";
                            }
                        }
                        tmp += " ";

                        if (numberBeforeDot.Substring(3, 1) != "1" && numberBeforeDot.Substring(3, 1) != "0")
                        {
                            string n2 = numberBeforeDot.Substring(4, 1);
                            tmp += SingleNumberToArabicName((int.Parse(n2)));
                            tmp += " و ";
                            string n3 = numberBeforeDot.Substring(3, 1);
                            tmp += SingleNumberToArabicName((int.Parse((n3 + '0'))));
                        }
                        else
                        {
                            if (numberBeforeDot.Substring(3, 1) == "0")
                            {
                                if (numberBeforeDot.Substring(4, 1) != "0")
                                {

                                    string n2 = numberBeforeDot.Substring(4, 1);
                                    tmp += SingleNumberToArabicName((int.Parse(n2)));
                                }
                            }
                            else
                            {
                                string n2 = numberBeforeDot.Substring(4, 1);
                                tmp += SingleNumberToArabicName((int.Parse(n2)));
                                tmp += "  ";
                                tmp += SingleNumberToArabicName(10);
                            }

                        }
                        myrturnString += tmp;
                        break;
                    }
            }
            #endregion


            myrturnString += " ";

            #region After .
            switch (lenghtofNumberAfterDt)
            {
                case 1:
                    {
                        if (numberAfterDot.Substring(0, 1) != "0")
                        {
                            myrturnString += SingleNumberToArabicName(int.Parse(numberAfterDot + "0"));
                            goto default;
                        }
                        break;
                    }
                case 2:
                    {
                        if (numberAfterDot.Substring(0, 1) != "0" && numberAfterDot.Substring(1, 1) != "0")
                        {
                            string tmp = "";
                            string n1 = numberAfterDot.Substring(1, 1);
                            tmp += SingleNumberToArabicName((int.Parse(n1)));
                            if (numberAfterDot.Substring(0, 1) != "1")
                            {
                                tmp += " و ";
                                string n2 = numberAfterDot.Substring(0, 1);
                                tmp += SingleNumberToArabicName((int.Parse((n2 + '0'))));
                            }
                            else
                            {
                                tmp += "  ";
                                tmp += SingleNumberToArabicName(10);

                            }
                            myrturnString += tmp;
                            goto default;
                        }
                        else
                        {
                            if (numberAfterDot.Substring(0, 1) == "0")
                            {
                                myrturnString += SingleNumberToArabicName(int.Parse(numberAfterDot.Substring(1, 1)));
                                goto default;
                            }
                            else
                            {
                                string tmp = "";
                                string n1 = numberAfterDot.Substring(1, 1);
                                tmp += SingleNumberToArabicName((int.Parse(n1)));
                                if (numberAfterDot.Substring(0, 1) != "1")
                                {
                                    tmp += " و ";
                                    string n2 = numberAfterDot.Substring(0, 1);
                                    tmp += SingleNumberToArabicName((int.Parse((n2 + '0'))));
                                }
                                else
                                {
                                    tmp += "  ";
                                    tmp += SingleNumberToArabicName(10);

                                }
                                myrturnString += tmp;
                                goto default;
                            }
                        }
                        break;
                    }
                default:
                    {
                        if (numberAfterDot != "")
                        {
                            myrturnString += " " + smallChangeCurrency + " ";
                        }
                        break;
                    }

            }
            #endregion

            myrturnString += " فقط لا غير ";


            return (myrturnString);
        }