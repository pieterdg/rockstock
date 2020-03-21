import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { RockstockSharedModule } from 'app/shared/shared.module';
import { NickelStrunzLevelThreeComponent } from './nickel-strunz-level-three.component';
import { NickelStrunzLevelThreeDetailComponent } from './nickel-strunz-level-three-detail.component';
import { NickelStrunzLevelThreeUpdateComponent } from './nickel-strunz-level-three-update.component';
import { NickelStrunzLevelThreeDeleteDialogComponent } from './nickel-strunz-level-three-delete-dialog.component';
import { nickelStrunzLevelThreeRoute } from './nickel-strunz-level-three.route';

@NgModule({
  imports: [RockstockSharedModule, RouterModule.forChild(nickelStrunzLevelThreeRoute)],
  declarations: [
    NickelStrunzLevelThreeComponent,
    NickelStrunzLevelThreeDetailComponent,
    NickelStrunzLevelThreeUpdateComponent,
    NickelStrunzLevelThreeDeleteDialogComponent
  ],
  entryComponents: [NickelStrunzLevelThreeDeleteDialogComponent]
})
export class RockstockNickelStrunzLevelThreeModule {}
