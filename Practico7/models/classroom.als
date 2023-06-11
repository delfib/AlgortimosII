/* The registered persons. */
sig Person  {
	/* Each person tutors a set of persons. */
	Tutors : set Person,
	/* Each person teaches a set of classes. */
	Teaches : set Class
}

/* The registered groups. */
sig Group {}

/* The registered classes. */
sig Class  {
	/* Each class has a set of persons assigned to a group. */
	Groups : Person -> Group
}

/* Some persons are teachers. */
sig Teacher extends Person  {}

/* Some persons are students. */
sig Student extends Person  {}


/* Every person is a student. */
// Toda persona pertence a Student, es un Student
pred inv1 {
	all p: Person | p in Student
}

/* There are no teachers. */
pred inv2 {
    // no Teacher
	all p: Person | p not in Teacher
}

run inv2

/* No person is both a student and a teacher. */
pred inv3 {
   all p: Person | p in Student => p not in Teacher and p in Teacher => p not in Student
}

/* No person is neither a student nor a teacher. */
pred inv4 {
    no Person & Teacher
	no Person & Student
}

pred inv4 {
	all p in Person | p !in Teacher and p !in Student
}

/* There are some classes assigned to teachers. */
pred inv5 {
   some Teacher.Teaches
}

/* Every teacher has classes assigned. */
pred inv6 {
    all t: Teacher | some t.Teaches
}

/* Every class has teachers assigned. */
pred inv7 {
	all c: Class | some t: Teacher | c in t.Teaches
}

/* Teachers are assigned at most one class. */
pred inv8 {
    all t: Teacher | lone c: Class | t.Teaches = c
}

/* No class has more than a teacher assigned. */
pred inv9 {
    all c: Class | lone t: Teacher | t.Teaches = c
}

/* For every class, every student has a group assigned. */
pred inv10 {
    all c: Class | all s: Student | some g: Group | (s->g) in c.Groups
}

/* A class only has groups if it has a teacher assigned. */
pred inv11 {
    all c: Class | some p: Person| some g: Group | some t: Teacher | t.Teaches = c and g in p.(c.Groups) 
}

/* Each teacher is responsible for some groups. */
pred inv12 {
    all t: Teacher | some p: Person | some g: Group | g in p.(t.Teaches.Groups)
}

/* Only teachers tutor, and only students are tutored. */
pred inv13 {
    all t: Teacher | all s: Student | t.Tutors and not s.Tutors and not t.Tutors = t and not s.Tutors = t
}

/* Every student in a class is at least tutored by all the teachers
 * assigned to that class. */
pred inv14 {
    all t: Teacher | all c: Class in (t.Teaches) | all s: Student in c | t.Tutors = s
}


/* The tutoring chain of every person eventually reaches a Teacher. */
pred inv15 {
    all p: Person | some t: Teacher | p in t.^Tutors	
}

run inv8 for 3 but exactly 3 Class

/*
assert checkIfPredIsOk {
	inv1 iff (inv_OK)
}
*/
